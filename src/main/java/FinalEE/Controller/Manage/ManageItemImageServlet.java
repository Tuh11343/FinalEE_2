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

public class ManageItemImageServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageItemImage.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        initData(req);
        switch (action) {
            case "addItemImage" -> {
                int itemID = Integer.parseInt(req.getParameter("add_itemImageItemID"));
                String url = req.getParameter("add_itemImageURL");

                Item item = itemServiceImpl.findByID(itemID);

                ItemImage itemImage = new ItemImage();
                itemImage.setImage_url(url);
                itemImage.setItem(item);

                if (itemImageServiceImpl.create(itemImage)) {
                    resp.getWriter().println("<script>alert('Thêm ảnh cho sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm ảnh cho sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemImageServlet");
            }
            case "updateItemImage" -> {
                int id = Integer.parseInt(req.getParameter("update_itemImageID"));
                int itemID = Integer.parseInt(req.getParameter("update_itemImageItemID"));
                String url = req.getParameter("update_itemImageURL");

                Item item = itemServiceImpl.findByID(itemID);

                ItemImage itemImage = new ItemImage();
                itemImage.setId(id);
                itemImage.setImage_url(url);
                itemImage.setItem(item);

                if (itemImageServiceImpl.create(itemImage)) {
                    resp.getWriter().println("<script>alert('Cập nhật ảnh cho sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật ảnh cho sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemImageServlet");
            }
            case "itemImage_btnDelete" -> {
                int itemImageID = Integer.parseInt(req.getParameter("itemImageID"));
                if (itemImageServiceImpl.deleteByID(itemImageID)) {
                    resp.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageItemImageServlet");
            }
            case "searchAndSortItemImage"-> {
                String searchType = req.getParameter("itemImageSearchType");
                String itemImageInputSearch = req.getParameter("itemImageInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer itemImageID = Integer.parseInt(req.getParameter("itemImageInputSearch"));
                        ItemImage itemImage = itemImageServiceImpl.findByID(itemImageID);
                        List<ItemImage> itemImageList = new ArrayList<>();
                        itemImageList.add(itemImage);

                        req.setAttribute("itemImageList", itemImageList);
                        req.getRequestDispatcher("Views/Admin/ManageItemImage.jsp").forward(req, resp);

                    }
                    case "itemID" -> {
                        String itemImageSortType = req.getParameter("itemImageSortType");
                        List<ItemImage> itemImageList = null;
                        if (itemImageSortType.equals("az")) {
                            itemImageList = itemImageServiceImpl.findAllByItemID(Integer.parseInt(itemImageInputSearch), "item_id", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemImageSortType.equals("za")) {
                            itemImageList = itemImageServiceImpl.findAllByItemID(Integer.parseInt(itemImageInputSearch), "item_id", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemImageList", itemImageList);
                        req.getRequestDispatcher("Views/Admin/ManageItemImage.jsp").forward(req, resp);
                    }
                }
            }

            case "refreshItemImage"->{
                List<ItemImage> itemImageList=itemImageServiceImpl.getAllItemImage();
                req.setAttribute("itemImageList", itemImageList);
                req.getRequestDispatcher("Views/Admin/ManageItemImage.jsp").forward(req, resp);
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
