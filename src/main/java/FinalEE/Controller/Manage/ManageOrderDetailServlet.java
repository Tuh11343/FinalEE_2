package FinalEE.Controller.Manage;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageOrderDetailServlet extends HttpServlet {

    private AccountServiceImpl accountServiceImpl;
    private CustomerServiceImpl customerServiceImpl;
    private DiscountCardServiceImpl discountCardServiceImpl;
    private ItemServiceImpl itemServiceImpl;
    private ItemCollectionServiceImpl itemCollectionServiceImpl;
    private ItemImageServiceImpl itemImageServiceImpl;
    private ItemMaterialServiceImpl itemMaterialServiceImpl;
    private OrderServiceImpl orderServiceImpl;
    private OrderDetailServiceImpl orderDetailServiceImpl;
    private ItemTypeServiceImpl itemTypeServiceImpl;
    private PermissionServiceImpl permissionServiceImpl;
    private SaleServiceImpl saleServiceImpl;
    private StockItemServiceImpl stockItemServiceImpl;
    private CartServiceImpl cartServiceImpl;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        initData(req);

        req.getRequestDispatcher("Views/Admin/ManageOrderDetail.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String action = req.getParameter("action");
            initData(req);
            switch (action) {
                case "addOrderDetail" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    Integer orderID=Integer.parseInt(req.getParameter("add_orderDetailOrderID"));
                    Integer stockItemID=Integer.parseInt(req.getParameter("add_orderDetailStockItemID"));
                    int amount=Integer.parseInt(req.getParameter("add_orderDetailAmount"));

                    Order order=orderServiceImpl.findByID(orderID);
                    StockItem stockItem=stockItemServiceImpl.findByID(stockItemID);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setStockItem(stockItem);
                    orderDetail.setAmount(amount);
                    orderDetail.setTotal(0);

                    if(!canOrder(orderDetail)){
                        jsonResponse.put("outOfStock",true);
                    }else{
                        if (orderDetailServiceImpl.create(orderDetail)) {
                            jsonResponse.put("success", true);
                        }
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "updateOrderDetail" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    Integer orderID=Integer.parseInt(req.getParameter("update_orderDetailOrderID"));
                    Integer stockItemID=Integer.parseInt(req.getParameter("update_orderDetailStockItemID"));
                    int amount=Integer.parseInt(req.getParameter("update_orderDetailAmount"));
                    double total=Double.parseDouble(req.getParameter("update_orderDetailTotal"));

                    Order order=orderServiceImpl.findByID(orderID);
                    StockItem stockItem=stockItemServiceImpl.findByID(stockItemID);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setStockItem(stockItem);
                    orderDetail.setAmount(amount);
                    orderDetail.setTotal(total);

                    if(!canOrder(orderDetail)){
                        jsonResponse.put("outOfStock",true);
                    }else{
                        if (orderDetailServiceImpl.create(orderDetail)) {
                            jsonResponse.put("success", true);
                        }
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "deleteOrderDetail" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int orderDetailID = Integer.parseInt(req.getParameter("orderDetailID"));
                    if (orderDetailServiceImpl.deleteByID(orderDetailID)) {
                        jsonResponse.put("success", true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "searchAndSortOrderDetail" -> {
                    String searchType = req.getParameter("orderDetailSearchType");
                    String orderInputSearch = req.getParameter("orderDetailInputSearch");
                    switch (searchType) {
                        case "noData" -> {

                        }
                        case "id" -> {
                            Integer orderDetailID = Integer.parseInt(req.getParameter("orderDetailInputSearch"));
                            OrderDetail orderDetail = orderDetailServiceImpl.findByID(orderDetailID);
                            List<OrderDetail> orderDetailList = new ArrayList<>();
                            orderDetailList.add(orderDetail);

                            initData(req);
                            req.setAttribute("orderDetailList", orderDetailList);
                            req.getRequestDispatcher("Views/Admin/ManageOrderDetail.jsp").forward(req, resp);

                        }
                        case "orderID" -> {
                            String orderDetailSortType = req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList = null;
                            if (orderDetailSortType.equals("az")) {
                                orderDetailList = orderDetailServiceImpl.findAllByOrderID(Integer.parseInt(orderInputSearch), "order_id", ItemServiceImpl.SortOrder.ASC);
                            } else if (orderDetailSortType.equals("za")) {
                                orderDetailList = orderDetailServiceImpl.findAllByOrderID(Integer.parseInt(orderInputSearch), "order_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            initData(req);
                            req.setAttribute("orderDetailList", orderDetailList);
                            req.getRequestDispatcher("Views/Admin/ManageOrderDetail.jsp").forward(req, resp);
                        }
                        case "lowerPrice" -> {
                            String orderDetailSortType = req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList = null;
                            if (orderDetailSortType.equals("az")) {
                                orderDetailList = orderDetailServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                            } else if (orderDetailSortType.equals("za")) {
                                orderDetailList = orderDetailServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                            }

                            initData(req);
                            req.setAttribute("orderDetailList", orderDetailList);
                            req.getRequestDispatcher("Views/Admin/ManageOrderDetail.jsp").forward(req, resp);
                        }
                        case "higherPrice" -> {
                            String orderDetailSortType = req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList = null;
                            if (orderDetailSortType.equals("az")) {
                                orderDetailList = orderDetailServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                            } else if (orderDetailSortType.equals("za")) {
                                orderDetailList = orderDetailServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                            }

                            initData(req);
                            req.setAttribute("orderDetailList", orderDetailList);
                            req.getRequestDispatcher("Views/Admin/ManageOrderDetail.jsp").forward(req, resp);
                        }
                    }
                }
            }

        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    private void initData(HttpServletRequest req) {
        ServletContext servletContext = getServletContext();

        accountServiceImpl = (AccountServiceImpl) servletContext.getAttribute("accountServiceImpl");
        cartServiceImpl = (CartServiceImpl) servletContext.getAttribute("cartServiceImpl");
        customerServiceImpl = (CustomerServiceImpl) servletContext.getAttribute("customerServiceImpl");
        discountCardServiceImpl = (DiscountCardServiceImpl) servletContext.getAttribute("discountCardServiceImpl");
        itemServiceImpl = (ItemServiceImpl) servletContext.getAttribute("itemServiceImpl");
        itemCollectionServiceImpl = (ItemCollectionServiceImpl) servletContext.getAttribute("itemCollectionServiceImpl");
        itemImageServiceImpl = (ItemImageServiceImpl) servletContext.getAttribute("itemImageServiceImpl");
        itemMaterialServiceImpl = (ItemMaterialServiceImpl) servletContext.getAttribute("itemMaterialServiceImpl");
        orderServiceImpl = (OrderServiceImpl) servletContext.getAttribute("orderServiceImpl");
        orderDetailServiceImpl = (OrderDetailServiceImpl) servletContext.getAttribute("orderDetailServiceImpl");
        itemTypeServiceImpl = (ItemTypeServiceImpl) servletContext.getAttribute("itemTypeServiceImpl");
        permissionServiceImpl = (PermissionServiceImpl) servletContext.getAttribute("permissionServiceImpl");
        saleServiceImpl = (SaleServiceImpl) servletContext.getAttribute("saleServiceImpl");
        stockItemServiceImpl = (StockItemServiceImpl) servletContext.getAttribute("stockItemServiceImpl");


        List<Account> accountList = accountServiceImpl.getAllAccount();
        List<Customer> customerList = customerServiceImpl.getAllCustomer();
        List<DiscountCard> discountCardList = discountCardServiceImpl.getAllDiscountCard();
        List<Item> itemList = itemServiceImpl.getAllItem();
        List<ItemCollection> itemCollectionList = itemCollectionServiceImpl.getAllItemCollection();
        List<ItemImage> imageList = itemImageServiceImpl.getAllItemImage();
        List<ItemMaterial> itemMaterialList = itemMaterialServiceImpl.getAllItemMaterial();
        List<Order> orderList = orderServiceImpl.getAllOrder();
        List<OrderDetail> orderDetailList = orderDetailServiceImpl.getAllOrderDetail();
        List<ItemType> itemTypeList = itemTypeServiceImpl.getAllItemType();
        List<Permission> permissionList = permissionServiceImpl.getAllPermission();
        List<Sale> saleList = saleServiceImpl.getAllSale();
        List<StockItem> stockItemList = stockItemServiceImpl.getAllStockItem();

        /*Set Data List*/
        req.setAttribute("accountList", accountList);
        req.setAttribute("customerList", customerList);
        req.setAttribute("discountCardList", discountCardList);
        req.setAttribute("itemList", itemList);
        req.setAttribute("itemCollectionList", itemCollectionList);
        req.setAttribute("imageList", imageList);
        req.setAttribute("itemMaterialList", itemMaterialList);
        req.setAttribute("orderList", orderList);
        req.setAttribute("orderDetailList", orderDetailList);
        req.setAttribute("itemTypeList", itemTypeList);
        req.setAttribute("permissionList", permissionList);
        req.setAttribute("saleList", saleList);
        req.setAttribute("stockItemList", stockItemList);
        req.setAttribute("cartServiceImpl", cartServiceImpl);

        //Lấy id account
        List<Cookie> cookieList = List.of(req.getCookies());
        for (Cookie cookie : cookieList) {
            if (cookie.getName().equals("signInAccountID")) {
                Integer signInAccountID = Integer.parseInt(cookie.getValue());
                Account account = accountServiceImpl.findByID(signInAccountID);
                req.setAttribute("signInAccount", account);
            }
        }
    }

    private boolean canOrder(OrderDetail orderDetail){
        try{
            if(orderDetail.getId()!=null){

                OrderDetail oldOrderDetail=orderDetailServiceImpl.findByID(orderDetail.getId());
                int oldAmount=oldOrderDetail.getAmount();
                int newAmount=orderDetail.getAmount();
                if(oldAmount>newAmount){
                    return true;
                }else if(oldAmount<newAmount){
                    int decrease=newAmount-oldAmount;
                    return decrease <= orderDetail.getStockItem().getAmount();
                }
            }else{
                return orderDetail.getAmount() <= orderDetail.getStockItem().getAmount();
            }
        }catch (Exception er){
            er.printStackTrace();
        }
        return false;
    }
}
