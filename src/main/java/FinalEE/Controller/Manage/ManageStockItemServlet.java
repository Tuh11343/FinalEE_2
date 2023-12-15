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

public class ManageStockItemServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageStockItem.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addStockItem" -> {
                int itemId = Integer.parseInt(req.getParameter("add_stockItemItemID"));
                String color = req.getParameter("add_stockItemColor");
                String size = req.getParameter("add_stockItemSize");
                int amount = Integer.parseInt(req.getParameter("add_stockItemAmount"));

                Item item = itemServiceImpl.getItem(itemId);

                StockItem stockItem = new StockItem();
                stockItem.setAmount(amount);
                stockItem.setSize(size);
                stockItem.setColor(color);
                stockItem.setItem(item);

                if (stockItemServiceImpl.create(stockItem)) {
                    resp.getWriter().println("<script>alert('Thêm hàng hóa sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm hàng hóa sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageStockItemServlet");
            }
            case "updateStockItem" -> {
                int id = Integer.parseInt(req.getParameter("update_stockItemID"));
                int itemId = Integer.parseInt(req.getParameter("update_stockItemItemID"));
                String color = req.getParameter("update_stockItemColor");
                String size = req.getParameter("update_stockItemSize");
                int amount = Integer.parseInt(req.getParameter("update_stockItemAmount"));

                Item item = itemServiceImpl.getItem(itemId);

                StockItem stockItem = new StockItem();
                stockItem.setId(id);
                stockItem.setAmount(amount);
                stockItem.setSize(size);
                stockItem.setColor(color);
                stockItem.setItem(item);

                if (stockItemServiceImpl.create(stockItem)) {
                    resp.getWriter().println("<script>alert('Cập nhật hàng hóa sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật hàng hóa sản phẩm thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageStockItemServlet");
            }
            case "deleteStockItem" -> {
                int stockItemID = Integer.parseInt(req.getParameter("stockItemID"));
                if (stockItemServiceImpl.deleteByID(stockItemID)) {
                    resp.getWriter().println("<script>alert('Xóa hàng hóa sản phẩm thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa hàng hóa sản phẩm thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageStockItemServlet");
            }
            case "searchAndSortStockItem" -> {
                String searchType = req.getParameter("stockItemSearchType");
                String stockItemInputSearch = req.getParameter("stockItemInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer stockItemID = Integer.parseInt(req.getParameter("stockItemInputSearch"));
                        StockItem stockItem = stockItemServiceImpl.getStockItem(stockItemID);
                        List<StockItem> stockItemList = new ArrayList<>();
                        stockItemList.add(stockItem);

                        req.setAttribute("stockItemList", stockItemList);
                        req.getRequestDispatcher("Views/Admin/ManageStockItem.jsp").forward(req, resp);

                    }
                    case "itemID" -> {
                        String stockItemSortType = req.getParameter("stockItemSortType");
                        List<StockItem> stockItemList = null;
                        if (stockItemSortType.equals("az")) {
                            stockItemList = stockItemServiceImpl.findAllByItemID(Integer.parseInt(stockItemInputSearch), "item_id", ItemServiceImpl.SortOrder.ASC);
                        } else if (stockItemSortType.equals("za")) {
                            stockItemList = stockItemServiceImpl.findAllByItemID(Integer.parseInt(stockItemInputSearch), "item_id", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("stockItemList", stockItemList);
                        req.getRequestDispatcher("Views/Admin/ManageStockItem.jsp").forward(req, resp);
                    }
                }

            }

            case "refreshStockItem"->{
                List<StockItem> stockItemList=stockItemServiceImpl.getAllStockItem();
                req.setAttribute("stockItemList", stockItemList);
                req.getRequestDispatcher("Views/Admin/ManageStockItem.jsp").forward(req, resp);
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
