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
import java.util.List;

public class ManageAccountServlet extends HttpServlet {

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

        req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter("action");
            initData(req);
            System.out.println(action);
            switch (action) {
                /*Account*/
                case "deleteAccount" -> {

                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int accountID = Integer.parseInt(req.getParameter("accountID"));
                    if (accountServiceImpl.deleteByID(accountID)) {
                        jsonResponse.put("success", true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "updateAccount" -> {

                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    int id = Integer.parseInt(req.getParameter("update_accountID"));
                    String name = req.getParameter("update_accountName");
                    String password = req.getParameter("update_accountPassword");
                    int permissionID = Integer.parseInt(req.getParameter("update_accountPermissionID"));
                    int customerID = Integer.parseInt(req.getParameter("update_accountCustomerID"));

                    Permission permission = permissionServiceImpl.findByID(permissionID);
                    Customer customer = customerServiceImpl.findByID(customerID);

                    Account account = new Account();
                    account.setId(id);
                    account.setName(name);
                    account.setCustomer(customer);
                    account.setPassword(password);
                    account.setPermission(permission);

                    if (accountServiceImpl.create(account)) {
                        jsonResponse.put("success",true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();

                }
                case "addAccount" -> {

                    System.out.println("Add account");

                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    String name = req.getParameter("add_accountName");
                    String password = req.getParameter("add_accountPassword");
                    int permissionID = Integer.parseInt(req.getParameter("add_accountPermissionID"));
                    int customerID = Integer.parseInt(req.getParameter("add_accountCustomerID"));

                    Customer customer = customerServiceImpl.findByID(customerID);
                    Permission permission = permissionServiceImpl.findByID(permissionID);

                    Account account = new Account();
                    account.setName(name);
                    account.setCustomer(customer);
                    account.setPassword(password);
                    account.setPermission(permission);

                    if (accountServiceImpl.create(account)) {
                        jsonResponse.put("success",true);
                    }

                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
                case "searchAndSortAccount" -> {
                    String searchType = req.getParameter("accountSearchType");
                    String accountInputSearch = req.getParameter("accountInputSearch");
                    switch (searchType) {
                        case "noData" -> {

                        }
                        case "id" -> {
                            Integer accountID = Integer.parseInt(req.getParameter("accountInputSearch"));
                            Account account = accountServiceImpl.findByID(accountID);
                            List<Account> accountList = new ArrayList<>();
                            accountList.add(account);

                            initData(req);
                            req.setAttribute("accountList", accountList);
                            req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);

                        }
                        case "name" -> {
                            String accountSortType = req.getParameter("accountSortType");
                            List<Account> accountList = null;
                            if (accountSortType.equals("az")) {
                                accountList = accountServiceImpl.findAllByNameContainsSort(accountInputSearch, "name", ItemServiceImpl.SortOrder.ASC);
                            } else if (accountSortType.equals("za")) {
                                accountList = accountServiceImpl.findAllByNameContainsSort(accountInputSearch, "name", ItemServiceImpl.SortOrder.DESC);
                            }

                            initData(req);
                            req.setAttribute("accountList", accountList);
                            req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);

                        }
                        case "customerID" -> {
                            Integer customerID = Integer.parseInt(req.getParameter("accountInputSearch"));
                            Account account = accountServiceImpl.findByCustomerID(customerID);
                            List<Account> accountList = new ArrayList<>();
                            accountList.add(account);

                            initData(req);
                            req.setAttribute("accountList", accountList);
                            req.getRequestDispatcher("Views/Admin/ManageAccount.jsp").forward(req, resp);

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
}
