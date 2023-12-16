package FinalEE.Controller.Manage;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        String action = req.getParameter("action");
        initData(req);
        switch (action) {
            /*Order*/
            case "addOrder" -> {
                int customerID = Integer.parseInt(req.getParameter("add_orderCustomerID"));
                int discountCardID = Integer.parseInt(req.getParameter("add_orderDiscountCardID"));
                double total = Double.parseDouble(req.getParameter("add_orderTotal"));
                String datePurchase = req.getParameter("add_orderDatePurchase");

                /*Xử lý định dạng date*/
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datePurchaseFormatted = null;
                try {
                    datePurchaseFormatted = inputFormat.parse(datePurchase);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Customer customer = customerServiceImpl.findByID(customerID);
                DiscountCard discountCard = discountCardServiceImpl.findByID(discountCardID);


                Order order = new Order();
                order.setCustomer(customer);
                order.setTotal(total);
                order.setDate_purchase(datePurchaseFormatted);
                order.setDiscountCard(discountCard);

                if (orderServiceImpl.create(order)) {
                    resp.getWriter().println("<script>alert('Thêm hóa đơn thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm hóa đơn thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageOrderDetailServlet");
            }
            case "order_btnUpdate" -> {
                int id = Integer.parseInt(req.getParameter("update_orderID"));
                int customerID = Integer.parseInt(req.getParameter("update_orderCustomerID"));
                int discountCardID = Integer.parseInt(req.getParameter("update_orderDiscountCardID"));
                double total = Double.parseDouble(req.getParameter("update_orderTotal"));
                String datePurchase = req.getParameter("update_orderDatePurchase");

                /*Xử lý định dạng date*/
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date datePurchaseFormatted = null;
                try {
                    datePurchaseFormatted = inputFormat.parse(datePurchase);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Customer customer = customerServiceImpl.findByID(customerID);
                DiscountCard discountCard = discountCardServiceImpl.findByID(discountCardID);

                Order order = new Order();
                order.setId(id);
                order.setCustomer(customer);
                order.setTotal(total);
                order.setDate_purchase(datePurchaseFormatted);
                order.setDiscountCard(discountCard);

                if (orderServiceImpl.create(order)) {
                    resp.getWriter().println("<script>alert('Cập nhật hóa đơn thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật hóa đơn thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageOrderDetailServlet");
            }
            case "order_btnDelete" -> {
                int orderID = Integer.parseInt(req.getParameter("orderID"));
                if (orderServiceImpl.deleteByID(orderID)) {
                    resp.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageOrderDetailServlet");
            }
            case "searchAndSortOrder"-> {
                String searchType = req.getParameter("orderSearchType");
                String orderInputSearch = req.getParameter("orderInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer orderID = Integer.parseInt(req.getParameter("orderInputSearch"));
                        Order order = orderServiceImpl.findByID(orderID);
                        List<Order> orderList = new ArrayList<>();
                        orderList.add(order);

                        req.setAttribute("orderList", orderList);
                        req.getRequestDispatcher("Views/Admin/ManageOrder.jsp").forward(req, resp);

                    }
                    case "customerID" -> {
                        String orderSortType = req.getParameter("orderSortType");
                        List<Order> orderList = null;
                        if (orderSortType.equals("az")) {
                            orderList = orderServiceImpl.findAllByCustomerID(Integer.parseInt(orderInputSearch), "customer_id", ItemServiceImpl.SortOrder.ASC);
                        } else if (orderSortType.equals("za")) {
                            orderList = orderServiceImpl.findAllByCustomerID(Integer.parseInt(orderInputSearch), "customer_id", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("orderList", orderList);
                        req.getRequestDispatcher("Views/Admin/ManageOrder.jsp").forward(req, resp);
                    }
                    case "lowerPrice" -> {
                        String orderSortType = req.getParameter("orderSortType");
                        List<Order> orderList = null;
                        if (orderSortType.equals("az")) {
                            orderList = orderServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                        } else if (orderSortType.equals("za")) {
                            orderList = orderServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("orderList", orderList);
                        req.getRequestDispatcher("Views/Admin/ManageOrder.jsp").forward(req, resp);
                    }
                    case "higherPrice" -> {
                        String orderSortType = req.getParameter("orderSortType");
                        List<Order> orderList = null;
                        if (orderSortType.equals("az")) {
                            orderList = orderServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                        } else if (orderSortType.equals("za")) {
                            orderList = orderServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("orderList", orderList);
                        req.getRequestDispatcher("Views/Admin/ManageOrder.jsp").forward(req, resp);
                    }
                }
            }
            case "refreshOrder"->{
                List<Order> orderList=orderServiceImpl.getAllOrder();
                req.setAttribute("orderList", orderList);
                req.getRequestDispatcher("Views/Admin/ManageOrder.jsp").forward(req, resp);
            }
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
                req.setAttribute("signInAccount",account);
            }
        }
    }
}
