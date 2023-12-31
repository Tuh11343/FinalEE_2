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
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageSaleServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageSale.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            initData(req);
            switch (action) {
                case "addSale" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int itemId = Integer.parseInt(req.getParameter("add_saleItemID"));
                    String name = req.getParameter("add_saleName");
                    int onSale = Integer.parseInt(req.getParameter("add_saleOnSale"));
                    double salePercentage = Double.parseDouble(req.getParameter("add_salePercentage"));

                    Item item = itemServiceImpl.findByID(itemId);

                    Sale sale = new Sale();
                    sale.setSale_percentage(salePercentage);
                    sale.setOn_sale(onSale);
                    sale.setItem(item);
                    sale.setName(name);

                    if (!canAdd(sale)) {
                        jsonResponse.put("saleExist", true);
                    } else {
                        if (saleServiceImpl.create(sale)) {
                            jsonResponse.put("success", true);
                        }
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "updateSale" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int id = Integer.parseInt(req.getParameter("update_saleID"));
                    int itemId = Integer.parseInt(req.getParameter("update_saleItemID"));
                    String name = req.getParameter("update_saleName");
                    int onSale = Integer.parseInt(req.getParameter("update_saleOnSale"));
                    double salePercentage = Double.parseDouble(req.getParameter("update_salePercentage"));

                    Item item = itemServiceImpl.findByID(itemId);

                    Sale sale = new Sale();
                    sale.setId(id);
                    sale.setSale_percentage(salePercentage);
                    sale.setOn_sale(onSale);
                    sale.setItem(item);
                    sale.setName(name);

                    if (saleServiceImpl.create(sale)) {
                        jsonResponse.put("success", true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "deleteSale" -> {
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int saleID = Integer.parseInt(req.getParameter("saleID"));
                    if (saleServiceImpl.deleteByID(saleID)) {
                        jsonResponse.put("success", true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "searchAndSortSale" -> {
                    String searchType = req.getParameter("saleSearchType");
                    String saleInputSearch = req.getParameter("saleInputSearch");
                    switch (searchType) {
                        case "noData" -> {

                        }
                        case "id" -> {
                            Integer saleID = Integer.parseInt(req.getParameter("saleInputSearch"));
                            Sale sale = saleServiceImpl.findBySale(saleID);
                            List<Sale> saleList = new ArrayList<>();
                            saleList.add(sale);

                            initData(req);
                            req.setAttribute("saleList", saleList);
                            req.getRequestDispatcher("Views/Admin/ManageSale.jsp").forward(req, resp);

                        }
                        case "name" -> {
                            String saleSortType = req.getParameter("saleSortType");
                            List<Sale> saleList = null;
                            if (saleSortType.equals("az")) {
                                saleList = saleServiceImpl.findAllByNameContains(saleInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                            } else if (saleSortType.equals("za")) {
                                saleList = saleServiceImpl.findAllByNameContains(saleInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                            }

                            initData(req);
                            req.setAttribute("saleList", saleList);
                            req.getRequestDispatcher("Views/Admin/ManageSale.jsp").forward(req, resp);
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
        req.setAttribute("cartServiceImpl", cartServiceImpl.getAllCart());

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

    private boolean canAdd(Sale sale) {
        if (saleServiceImpl.findByItemID(sale.getItem().getId()) != null) {
            return false;
        }
        return true;
    }
}
