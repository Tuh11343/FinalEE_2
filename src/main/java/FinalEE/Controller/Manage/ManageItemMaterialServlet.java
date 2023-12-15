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

public class ManageItemMaterialServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageItemMaterial.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addItemMaterial" -> {
                String name = req.getParameter("add_itemMaterialName");

                ItemMaterial itemMaterial = new ItemMaterial();
                itemMaterial.setName(name);

                if (itemMaterialServiceImpl.create(itemMaterial)) {
                    resp.getWriter().println("<script>alert('Thêm vật liệu thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Thêm vật liệu thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemMaterialServlet");
            }
            case "updateItemMaterial" -> {
                int id = Integer.parseInt(req.getParameter("update_itemMaterialID"));
                String name = req.getParameter("update_itemMaterialName");

                ItemMaterial itemMaterial = new ItemMaterial();
                itemMaterial.setId(id);
                itemMaterial.setName(name);

                if (itemMaterialServiceImpl.create(itemMaterial)) {
                    resp.getWriter().println("<script>alert('Cập nhật vật liệu thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Cập nhật vật liệu thất bại!');</script>");
                }

                resp.sendRedirect("/FinalEE/ManageItemMaterialServlet");
            }
            case "itemMaterial_btnDelete" -> {
                int itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialID"));
                if (itemMaterialServiceImpl.deleteByID(itemMaterialID)) {
                    resp.getWriter().println("<script>alert('Xóa vật liệu thành công!');</script>");
                } else {
                    resp.getWriter().println("<script>alert('Xóa vật liệu thất bại!');</script>");
                }
                resp.sendRedirect("/FinalEE/ManageItemMaterialServlet");
            }
            case "searchAndSortItemMaterial"-> {
                String searchType = req.getParameter("itemMaterialSearchType");
                String itemMaterialInputSearch = req.getParameter("itemMaterialInputSearch");
                switch (searchType) {
                    case "noData" -> {

                    }
                    case "id" -> {
                        Integer itemMaterialID = Integer.parseInt(req.getParameter("itemMaterialInputSearch"));
                        ItemMaterial itemMaterial = itemMaterialServiceImpl.getItemMaterial(itemMaterialID);
                        List<ItemMaterial> itemMaterialList = new ArrayList<>();
                        itemMaterialList.add(itemMaterial);

                        req.setAttribute("itemMaterialList", itemMaterialList);
                        req.getRequestDispatcher("Views/Admin/ManageItemMaterial.jsp").forward(req, resp);

                    }
                    case "name" -> {
                        String itemMaterialSortType = req.getParameter("itemMaterialSortType");
                        List<ItemMaterial> itemMaterialList = null;
                        if (itemMaterialSortType.equals("az")) {
                            itemMaterialList = itemMaterialServiceImpl.findAllByNameContains(itemMaterialInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                        } else if (itemMaterialSortType.equals("za")) {
                            itemMaterialList = itemMaterialServiceImpl.findAllByNameContains(itemMaterialInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                        }

                        req.setAttribute("itemMaterialList", itemMaterialList);
                        req.getRequestDispatcher("Views/Admin/ManageItemMaterial.jsp").forward(req, resp);
                    }
                }
            }

            case "refreshItem"->{
                List<ItemMaterial> itemMaterialList=itemMaterialServiceImpl.getAllItemMaterial();
                req.setAttribute("itemMaterialList", itemMaterialList);
                req.getRequestDispatcher("Views/Admin/ManageItemMaterial.jsp").forward(req, resp);
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
