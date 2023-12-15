package FinalEE.Controller.Manage;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageOrderStatusServlet extends HttpServlet {

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
    private OrderStatusServiceImpl orderStatusServiceImpl;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        initData(req);

        req.getRequestDispatcher("Views/Admin/ManageOrderStatus.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addOrderStatus" -> {
                String name = req.getParameter("add_orderStatusName");

                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setName(name);

                if (orderStatusServiceImpl.create(orderStatus)) {
                    resp.getWriter().println("<script>alert('Thêm trạng thái đơn hàng thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm trạng thái đơn hàng thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageOrderStatusServlet");
            }
            case "updateOrderStatus" -> {
                int id = Integer.parseInt(req.getParameter("update_orderStatusID"));
                String name = req.getParameter("update_orderStatusName");

                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(id);
                orderStatus.setName(name);

                if (orderStatusServiceImpl.create(orderStatus)) {
                    resp.getWriter().println("<script>alert('Cập nhật trạng thái đơn hàng thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật trạng thái đơn hàng thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageOrderStatusServlet");
            }
            case "deleteOrderStatus" -> {
                int orderStatusID = Integer.parseInt(req.getParameter("orderStatusID"));
                if (orderStatusServiceImpl.deleteByID(orderStatusID)) {
                    resp.getWriter().println("<script>alert('Xóa loại sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa loại sản phẩm thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageOrderStatusServlet");
            }
            case "searchAndSortOrderStatus" -> {
                String searchType = req.getParameter("orderStatusSearchType");
                String orderStatusInputSearch = req.getParameter("orderStatusInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer orderStatusID = Integer.parseInt(req.getParameter("orderStatusInputSearch"));
                        OrderStatus orderStatus = orderStatusServiceImpl.findById(orderStatusID);
                        List<OrderStatus> orderStatusList = new ArrayList<>();
                        orderStatusList.add(orderStatus);

                        req.setAttribute("orderStatusList", orderStatusList);
                        req.getRequestDispatcher("Views/Admin/ManageOrderStatus.jsp").forward(req, resp);

                    }
                    case "name" -> {
                        String orderStatusSortType = req.getParameter("orderStatusSortType");
                        List<OrderStatus> orderStatusList = null;
                        if (orderStatusSortType.equals("az")) {
                            orderStatusList = orderStatusServiceImpl.findAllByNameContains(orderStatusInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                        } else if (orderStatusSortType.equals("za")) {
                            orderStatusList = orderStatusServiceImpl.findAllByNameContains(orderStatusInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("orderStatusList", orderStatusList);
                        req.getRequestDispatcher("Views/Admin/ManageOrderStatus.jsp").forward(req, resp);
                    }
                }

            }

            case "refreshOrderStatus"->{
                List<OrderStatus> orderStatusList=orderStatusServiceImpl.getAllOrderStatus();
                req.setAttribute("orderStatusList", orderStatusList);
                req.getRequestDispatcher("Views/Admin/ManageOrderStatus.jsp").forward(req, resp);
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
        orderStatusServiceImpl= (OrderStatusServiceImpl) servletContext.getAttribute("orderStatusServiceImpl");


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
        List<OrderStatus> orderStatusList=orderStatusServiceImpl.getAllOrderStatus();

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
        req.setAttribute("orderStatusList",orderStatusList);
    }
}
