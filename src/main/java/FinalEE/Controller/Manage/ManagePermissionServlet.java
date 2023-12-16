package FinalEE.Controller.Manage;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePermissionServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManagePermission.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        initData(req);
        switch (action) {
            case "addPermission" -> {
                int level = Integer.parseInt(req.getParameter("add_permissionLevel"));
                String name = req.getParameter("add_permissionName");

                Permission permission = new Permission();
                permission.setLevel(level);
                permission.setName(name);

                if (permissionServiceImpl.create(permission)) {
                    resp.getWriter().println("<script>alert('Thêm quyền thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm quyền thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManagePermissionServlet");
            }
            case "updatePermission" -> {
                int id = Integer.parseInt(req.getParameter("update_permissionID"));
                String name = req.getParameter("update_permissionName");
                int level = Integer.parseInt(req.getParameter("update_permissionLevel"));

                Permission permission = new Permission();
                permission.setId(id);
                permission.setLevel(level);
                permission.setName(name);

                if (permissionServiceImpl.create(permission)) {
                    resp.getWriter().println("<script>alert('Cập nhật quyền thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật quyền thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManagePermissionServlet");
            }
            case "deletePermission" -> {
                int permissionID = Integer.parseInt(req.getParameter("permissionID"));
                if (permissionServiceImpl.deleteByID(permissionID)) {
                    resp.getWriter().println("<script>alert('Xóa vật liệu thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa vật liệu thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManagePermissionServlet");
            }
            case "searchAndSortPermission" -> {
                String searchType = req.getParameter("permissionSearchType");
                String permissionInputSearch = req.getParameter("permissionInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer permissionID = Integer.parseInt(req.getParameter("permissionInputSearch"));
                        Permission permission = permissionServiceImpl.findByID(permissionID);
                        List<Permission> permissionList = new ArrayList<>();
                        permissionList.add(permission);

                        req.setAttribute("permissionList", permissionList);
                        req.getRequestDispatcher("Views/Admin/ManagePermission.jsp").forward(req, resp);

                    }
                    case "name" -> {
                        String permissionSortType = req.getParameter("permissionSortType");
                        List<Permission> permissionList = null;
                        if (permissionSortType.equals("az")) {
                            permissionList = permissionServiceImpl.findAllByNameContains(permissionInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                        } else if (permissionSortType.equals("za")) {
                            permissionList = permissionServiceImpl.findAllByNameContains(permissionInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("permissionList", permissionList);
                        req.getRequestDispatcher("Views/Admin/ManagePermission.jsp").forward(req, resp);
                    }
                }

            }

            case "refreshPermission"->{
                List<Permission> permissionList=permissionServiceImpl.getAllPermission();
                req.setAttribute("permissionList", permissionList);
                req.getRequestDispatcher("Views/Admin/ManagePermission.jsp").forward(req, resp);
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
