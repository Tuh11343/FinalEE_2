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

public class ManageItemTypeServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageItemType.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        initData(req);
        switch (action) {
            case "addItemType" -> {
                String name = req.getParameter("add_itemTypeName");

                ItemType itemType = new ItemType();
                itemType.setName(name);

                if (itemTypeServiceImpl.create(itemType)) {
                    resp.getWriter().println("<script>alert('Thêm loại sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm loại sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemTypeServlet");
            }
            case "updateItemType" -> {
                int id = Integer.parseInt(req.getParameter("update_itemTypeID"));
                String name = req.getParameter("update_itemTypeName");

                ItemType itemType = new ItemType();
                itemType.setId(id);
                itemType.setName(name);

                if (itemTypeServiceImpl.create(itemType)) {
                    resp.getWriter().println("<script>alert('Cập nhật loại sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật loại sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemTypeServlet");
            }
            case "deleteItemType" -> {
                int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
                if (itemTypeServiceImpl.deleteByID(itemTypeID)) {
                    resp.getWriter().println("<script>alert('Xóa loại sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa loại sản phẩm thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageItemTypeServlet");

            }
            case "searchAndSortItemType"-> {
                String searchType = req.getParameter("itemTypeSearchType");
                String itemTypeInputSearch = req.getParameter("itemTypeInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer itemTypeID = Integer.parseInt(req.getParameter("itemTypeInputSearch"));
                        ItemType itemType = itemTypeServiceImpl.findByID(itemTypeID);
                        List<ItemType> itemTypeList = new ArrayList<>();
                        itemTypeList.add(itemType);

                        req.setAttribute("itemTypeList", itemTypeList);
                        req.getRequestDispatcher("Views/Admin/ManageItemType.jsp").forward(req, resp);

                    }
                    case "name" -> {
                        String itemTypeSortType = req.getParameter("itemTypeSortType");
                        List<ItemType> itemTypeList = null;
                        if (itemTypeSortType.equals("az")) {
                            itemTypeList = itemTypeServiceImpl.findAllByNameContains(itemTypeInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemTypeSortType.equals("za")) {
                            itemTypeList = itemTypeServiceImpl.findAllByNameContains(itemTypeInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemTypeList", itemTypeList);
                        req.getRequestDispatcher("Views/Admin/ManageItemType.jsp").forward(req, resp);
                    }
                }
            }
            case "refreshItemType"->{
                List<ItemType> itemTypeList=itemTypeServiceImpl.getAllItemType();
                req.setAttribute("itemTypeList", itemTypeList);
                req.getRequestDispatcher("Views/Admin/ManageItemType.jsp").forward(req, resp);
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
