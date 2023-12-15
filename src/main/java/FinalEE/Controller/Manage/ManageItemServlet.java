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

public class ManageItemServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageItem.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addItem" -> {

                String name = req.getParameter("add_itemName");
                int itemTypeID = Integer.parseInt(req.getParameter("add_itemTypeID"));
                int itemMaterialID = Integer.parseInt(req.getParameter("add_itemMaterialID"));
                int itemCollectionID = Integer.parseInt(req.getParameter("add_itemCollectionID"));
                int isNew = Integer.parseInt(req.getParameter("add_itemIsNew"));
                int isHot = Integer.parseInt(req.getParameter("add_itemIsHot"));
                double price = Double.parseDouble(req.getParameter("add_itemPrice"));
                int yearProduce = Integer.parseInt(req.getParameter("add_itemYearProduce"));

                ItemCollection itemCollection = itemCollectionServiceImpl.getItemCollection(itemCollectionID);
                ItemMaterial itemMaterial = itemMaterialServiceImpl.getItemMaterial(itemMaterialID);
                ItemType itemType = itemTypeServiceImpl.getItemType(itemTypeID);

                Item item = new Item();
                item.setIs_hot(isHot);
                item.setIs_new(isNew);
                item.setItemCollection(itemCollection);
                item.setItemMaterial(itemMaterial);
                item.setItemType(itemType);
                item.setName(name);
                item.setPrice(price);
                item.setYear_produce(yearProduce);

                if (itemServiceImpl.create(item)) {
                    resp.getWriter().println("<script>alert('Thêm sản phẩm thành công!');</script>");

                } else {
                    resp.getWriter().println("<script>alert('Thêm sản phẩm thất bại!');</script>");
                }

                req.getRequestDispatcher("Views/Admin/ManageItem.jsp").forward(req, resp);
            }
            case "updateItem" -> {

                int id = Integer.parseInt(req.getParameter("update_itemID"));
                String name = req.getParameter("update_itemName");
                int itemTypeID = Integer.parseInt(req.getParameter("update_itemTypeID"));
                int itemMaterialID = Integer.parseInt(req.getParameter("update_itemMaterialID"));
                int itemCollectionID = Integer.parseInt(req.getParameter("update_itemCollectionID"));
                int isNew = Integer.parseInt(req.getParameter("update_itemIsNew"));
                int isHot = Integer.parseInt(req.getParameter("update_itemIsHot"));
                double price = Double.parseDouble(req.getParameter("update_itemPrice"));
                int yearProduce = Integer.parseInt(req.getParameter("update_itemYearProduce"));

                ItemCollection itemCollection = itemCollectionServiceImpl.getItemCollection(itemCollectionID);
                ItemMaterial itemMaterial = itemMaterialServiceImpl.getItemMaterial(itemMaterialID);
                ItemType itemType = itemTypeServiceImpl.getItemType(itemTypeID);

                Item item = new Item();
                item.setId(id);
                item.setIs_hot(isHot);
                item.setIs_new(isNew);
                item.setItemCollection(itemCollection);
                item.setItemMaterial(itemMaterial);
                item.setItemType(itemType);
                item.setName(name);
                item.setPrice(price);
                item.setYear_produce(yearProduce);

                if (itemServiceImpl.create(item)) {
                    resp.getWriter().println("<script>alert('Cập nhật sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật sản phẩm thất bại!');</script>");
                }

                req.getRequestDispatcher("Views/Admin/ManageItem.jsp").forward(req, resp);
            }
            case "deleteItem" -> {
                int itemID = Integer.parseInt(req.getParameter("itemID"));
                if (itemServiceImpl.deleteByID(itemID)) {
                    resp.getWriter().println("<script>alert('Xóa sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa sản phẩm thất bại!');</script>");
                }
                req.getRequestDispatcher("Views/Admin/ManageItem.jsp").forward(req, resp);
            }
            case "searchAndSortItem"-> {
                String searchType = req.getParameter("itemSearchType");
                String itemInputSearch = req.getParameter("itemInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer itemID = Integer.parseInt(req.getParameter("itemInputSearch"));
                        Item item = itemServiceImpl.getItem(itemID);
                        List<Item> itemList = new ArrayList<>();
                        itemList.add(item);

                        req.setAttribute("itemList", itemList);
                        resp.sendRedirect("/FinalEE/ManageItemServlet");

                    }
                    case "itemColor" -> {
                        String itemSortType = req.getParameter("itemSortType");
                        List<Item> itemList = null;
                        if (itemSortType.equals("az")) {
                            itemList = itemServiceImpl.findItemListByColor(itemInputSearch, "item_id", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemSortType.equals("za")) {
                            itemList = itemServiceImpl.findItemListByColor(itemInputSearch, "item_id", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemList", itemList);
                        resp.sendRedirect("/FinalEE/ManageItemServlet");
                    }
                    case "lowerPrice" -> {
                        String itemSortType = req.getParameter("itemSortType");
                        List<Item> itemList = null;
                        if (itemSortType.equals("az")) {
                            itemList = itemServiceImpl.findAllByPriceLessThan(Double.parseDouble(itemInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemSortType.equals("za")) {
                            itemList = itemServiceImpl.findAllByPriceLessThan(Double.parseDouble(itemInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemList", itemList);
                        resp.sendRedirect("/FinalEE/ManageItemServlet");
                    }
                    case "higherPrice" -> {
                        String itemSortType = req.getParameter("itemSortType");
                        List<Item> itemList = null;
                        if (itemSortType.equals("az")) {
                            itemList = itemServiceImpl.findAllByPriceGreaterThan(Double.parseDouble(itemInputSearch), "total", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemSortType.equals("za")) {
                            itemList = itemServiceImpl.findAllByPriceGreaterThan(Double.parseDouble(itemInputSearch), "total", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemList", itemList);
                        resp.sendRedirect("/FinalEE/ManageItemServlet");
                    }
                }
            }

            case "refreshItem"->{
                List<Item> itemList=itemServiceImpl.getAllItem();
                req.setAttribute("itemList", itemList);
                resp.sendRedirect("/FinalEE/ManageItemServlet");
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
    }
}
