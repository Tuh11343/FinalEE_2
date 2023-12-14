package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServlet extends HttpServlet {

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
    private StatisticServiceImpl statisticServiceIml;
    private OrderStatusServiceImpl orderStatusServiceImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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
        statisticServiceIml = (StatisticServiceImpl) servletContext.getAttribute("statisticServiceImpl");


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

        req.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String action=req.getParameter("action");
            switch (action){
                /*Customer*/
                case "addCustomer"->{
                    System.out.println("Thêm khách hàng");

                    ObjectMapper mapper=new ObjectMapper();

                    String name = req.getParameter("add_customerName");
                    String phoneNumber = req.getParameter("add_customerPhoneNumber");
                    String email = req.getParameter("add_customerEmail");
                    String address = req.getParameter("add_customerAddress");

                    Customer customer = new Customer();
                    customer.setAddress(address);
                    customer.setEmail(email);
                    customer.setName(name);
                    customer.setPhone_number(phoneNumber);

                    if (customerServiceImpl.create(customer)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("customer", customer);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateCustomer"->{
                    int id = Integer.parseInt(req.getParameter("update_customerID"));
                    String name = req.getParameter("update_customerName");
                    String phoneNumber = req.getParameter("update_customerPhoneNumber");
                    String email = req.getParameter("update_customerEmail");
                    String address = req.getParameter("update_customerAddress");

                    Customer customer = new Customer();
                    customer.setId(id);
                    customer.setAddress(address);
                    customer.setEmail(email);
                    customer.setName(name);
                    customer.setPhone_number(phoneNumber);

                    if (customerServiceImpl.create(customer)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("customer", customer);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteCustomer"->{
                    System.out.println("Xóa khách hàng");

                    Integer customerID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (customerServiceImpl.deleteByID(customerID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortCustomer"->{
                    String searchType=req.getParameter("customerSearchType");
                    String customerInputSearch=req.getParameter("customerInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer customerID=Integer.parseInt(req.getParameter("customerInputSearch"));
                            Customer customer=customerServiceImpl.getCustomer(customerID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("customer",customer);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String customerSortType=req.getParameter("customerSortType");
                            List<Customer> customerList=null;
                            if(customerSortType.equals("az")){
                                customerList=customerServiceImpl.findAllByNameLike(customerInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(customerSortType.equals("za")){
                                customerList=customerServiceImpl.findAllByNameLike(customerInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("customerList",customerList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Account*/
                case "addAccount"->{
                    String name = req.getParameter("add_accountName");
                    String password = req.getParameter("add_accountPassword");
                    int permissionID = Integer.parseInt(req.getParameter("add_accountPermissionID"));
                    int customerID = Integer.parseInt(req.getParameter("add_accountCustomerID"));

                    Customer customer = customerServiceImpl.getCustomer(customerID);
                    Permission permission = permissionServiceImpl.getPermission(permissionID);

                    Account account = new Account();
                    account.setName(name);
                    account.setCustomer(customer);
                    account.setPassword(password);
                    account.setPermission(permission);

                    if (accountServiceImpl.create(account)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("account", account);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateAccount"->{
                    int id = Integer.parseInt(req.getParameter("update_accountID"));
                    String name = req.getParameter("update_accountName");
                    String password = req.getParameter("update_accountPassword");
                    int permissionID = Integer.parseInt(req.getParameter("update_accountPermissionID"));
                    int customerID = Integer.parseInt(req.getParameter("update_accountCustomerID"));

                    Permission permission = permissionServiceImpl.getPermission(permissionID);
                    Customer customer = customerServiceImpl.getCustomer(customerID);

                    Account account = new Account();
                    account.setId(id);
                    account.setName(name);
                    account.setCustomer(customer);
                    account.setPassword(password);
                    account.setPermission(permission);

                    if (accountServiceImpl.create(account)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("account", account);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteAccount"->{
                    System.out.println("Xóa tài khoản");

                    Integer accountID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (accountServiceImpl.deleteByID(accountID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortAccount"->{
                    String searchType=req.getParameter("accountSearchType");
                    String accountInputSearch=req.getParameter("accountInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer accountID=Integer.parseInt(req.getParameter("accountInputSearch"));
                            Account account=accountServiceImpl.getAccount(accountID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("account",account);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String accountSortType=req.getParameter("accountSortType");
                            List<Account> accountList=null;
                            if(accountSortType.equals("az")){
                                accountList=accountServiceImpl.findAllByNameContainsSort(accountInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(accountSortType.equals("za")){
                                accountList=accountServiceImpl.findAllByNameContainsSort(accountInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("accountList",accountList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "customerID"->{
                            Integer customerID=Integer.parseInt(req.getParameter("accountInputSearch"));
                            Account account=accountServiceImpl.findByCustomerID(customerID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("account",account);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Cart*/
                case "addCart"->{
                    System.out.println("Thêm giỏ hàng");

                    ObjectMapper mapper=new ObjectMapper();
                    Cart cart=mapper.readValue(req.getParameter("cart"),Cart.class);

                    if (cartServiceImpl.create(cart)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("cart", cart);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateCart"->{
                    System.out.println("Cập nhật giỏ hàng");

                    ObjectMapper mapper=new ObjectMapper();
                    Cart cart=mapper.readValue(req.getParameter("cart"),Cart.class);

                    if (cartServiceImpl.create(cart)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("cart", cart);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteCart"->{
                    System.out.println("Xóa giỏ hàng");

                    Integer cartID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (cartServiceImpl.deleteByID(cartID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortCart"->{
                    String searchType=req.getParameter("cartSearchType");
                    String cartInputSearch=req.getParameter("cartInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer cartID=Integer.parseInt(req.getParameter("cartInputSearch"));
                            Cart cart=cartServiceImpl.getCart(cartID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("cart",cart);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "customerID"->{
                            String cartSortType=req.getParameter("cartSortType");
                            List<Cart> cartList=null;
                            if(cartSortType.equals("az")){
                                cartList=cartServiceImpl.findByCustomerID(Integer.parseInt(cartInputSearch),"customer_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(cartSortType.equals("za")){
                                cartList=cartServiceImpl.findByCustomerID(Integer.parseInt(cartInputSearch),"customer_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("cartList",cartList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }

                    }

                }

                /*Discount Card*/
                case "addDiscountCard"->{
                    System.out.println("Thêm thẻ khuyến mãi");

                    int customerID = Integer.parseInt(req.getParameter("add_discountCardID"));
                    String name = req.getParameter("add_discountCardName");
                    int discountPercentage = Integer.parseInt(req.getParameter("add_discountCardDiscountPercentage"));

                    Customer customer = customerServiceImpl.getCustomer(customerID);

                    DiscountCard discountCard = new DiscountCard();
                    discountCard.setCustomer(customer);
                    discountCard.setDiscount_percentage(discountPercentage);
                    discountCard.setName(name);

                    if (discountCardServiceImpl.create(discountCard)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("discountCard", discountCard);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateDiscountCard"->{
                    int id = Integer.parseInt(req.getParameter("update_discountCardID"));
                    int discountPercentage = Integer.parseInt(req.getParameter("update_discountCardDiscountPercentage"));
                    String name = req.getParameter("update_discountCardName");
                    int customerID = Integer.parseInt(req.getParameter("update_discountCardID"));

                    Customer customer = customerServiceImpl.getCustomer(customerID);

                    DiscountCard discountCard = new DiscountCard();
                    discountCard.setId(id);
                    discountCard.setCustomer(customer);
                    discountCard.setDiscount_percentage(discountPercentage);
                    discountCard.setName(name);

                    if (discountCardServiceImpl.create(discountCard)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("discountCard", discountCard);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteDiscountCard"->{
                    System.out.println("Xóa thẻ khuyến mãi");

                    Integer discountCardID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (discountCardServiceImpl.deleteByID(discountCardID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortDiscountCard"->{
                    String searchType=req.getParameter("discountCardSearchType");
                    String discountCardInputSearch=req.getParameter("discountCardInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer discountCardID=Integer.parseInt(req.getParameter("discountCardInputSearch"));
                            DiscountCard discountCard=discountCardServiceImpl.getDiscountCard(discountCardID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("discountCard",discountCard);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String discountCardSortType=req.getParameter("discountCardSortType");
                            List<DiscountCard> discountCardList=null;
                            if(discountCardSortType.equals("az")){
                                discountCardList=discountCardServiceImpl.findAllByNameLikeSort(discountCardInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(discountCardSortType.equals("za")){
                                discountCardList=discountCardServiceImpl.findAllByNameLikeSort(discountCardInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("discountCardList",discountCardList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "customerID"->{
                            String discountCardSortType=req.getParameter("discountCardSortType");
                            List<DiscountCard> discountCardList=null;
                            if(discountCardSortType.equals("az")){
                                discountCardList=discountCardServiceImpl.findByCustomerID(Integer.parseInt(discountCardInputSearch),"customer_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(discountCardSortType.equals("za")){
                                discountCardList=discountCardServiceImpl.findByCustomerID(Integer.parseInt(discountCardInputSearch),"customer_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("discountCardList",discountCardList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*ItemCollection*/
                case "addItemCollection"->{
                    String name = req.getParameter("add_itemCollectionName");

                    ItemCollection itemCollection = new ItemCollection();
                    itemCollection.setName(name);

                    if (itemCollectionServiceImpl.create(itemCollection)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemCollection", itemCollection);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemCollection"->{
                    int id = Integer.parseInt(req.getParameter("update_itemCollectionID"));
                    String name = req.getParameter("update_itemCollectionName");

                    ItemCollection itemCollection = new ItemCollection();
                    itemCollection.setId(id);
                    itemCollection.setName(name);

                    if (itemCollectionServiceImpl.create(itemCollection)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemCollection", itemCollection);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteItemCollection"->{
                    System.out.println("Xóa bộ sưu tập sản phẩm");

                    Integer itemCollectionID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (itemCollectionServiceImpl.deleteByID(itemCollectionID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortItemCollection"->{
                    String searchType=req.getParameter("itemCollectionSearchType");
                    String itemCollectionInputSearch=req.getParameter("itemCollectionInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer itemCollectionID=Integer.parseInt(req.getParameter("itemCollectionInputSearch"));
                            ItemCollection itemCollection=itemCollectionServiceImpl.getItemCollection(itemCollectionID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemCollection",itemCollection);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String itemCollectionSortType=req.getParameter("itemCollectionSortType");
                            List<ItemCollection> itemCollectionList=null;
                            if(itemCollectionSortType.equals("az")){
                                itemCollectionList=itemCollectionServiceImpl.findAllByNameContains(itemCollectionInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemCollectionSortType.equals("za")){
                                itemCollectionList=itemCollectionServiceImpl.findAllByNameContains(itemCollectionInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemCollectionList",itemCollectionList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*ItemImage*/
                case "addItemImage"->{
                    int itemID = Integer.parseInt(req.getParameter("add_itemImageItemID"));
                    String url = req.getParameter("add_itemImageURL");

                    Item item = itemServiceImpl.getItem(itemID);

                    ItemImage itemImage = new ItemImage();
                    itemImage.setImage_url(url);
                    itemImage.setItem(item);

                    if (itemImageServiceImpl.create(itemImage)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemImage", itemImage);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemImage"->{
                    int id = Integer.parseInt(req.getParameter("update_itemImageID"));
                    int itemID = Integer.parseInt(req.getParameter("update_itemImageItemID"));
                    String url = req.getParameter("update_itemImageURL");

                    Item item = itemServiceImpl.getItem(itemID);

                    ItemImage itemImage = new ItemImage();
                    itemImage.setId(id);
                    itemImage.setImage_url(url);
                    itemImage.setItem(item);

                    if (itemImageServiceImpl.create(itemImage)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemImage", itemImage);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteItemImage"->{
                    System.out.println("Xóa hình ảnh sản phẩm");

                    Integer itemImageID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (itemImageServiceImpl.deleteByID(itemImageID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortItemImage"->{
                    String searchType=req.getParameter("itemImageSearchType");
                    String itemImageInputSearch=req.getParameter("itemImageInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer itemImageID=Integer.parseInt(req.getParameter("itemImageInputSearch"));
                            ItemImage itemImage=itemImageServiceImpl.getItemImage(itemImageID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemImage",itemImage);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "itemID"->{
                            String itemImageSortType=req.getParameter("itemImageSortType");
                            List<ItemImage> itemImageList=null;
                            if(itemImageSortType.equals("az")){
                                itemImageList=itemImageServiceImpl.findAllByItemID(Integer.parseInt(itemImageInputSearch),"item_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemImageSortType.equals("za")){
                                itemImageList=itemImageServiceImpl.findAllByItemID(Integer.parseInt(itemImageInputSearch),"item_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemImageList",itemImageList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*ItemMaterial*/
                case "addItemMaterial"->{
                    String name = req.getParameter("add_itemMaterialName");

                    ItemMaterial itemMaterial = new ItemMaterial();
                    itemMaterial.setName(name);

                    if (itemMaterialServiceImpl.create(itemMaterial)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemMaterial", itemMaterial);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemMaterial"->{
                    int id = Integer.parseInt(req.getParameter("update_itemMaterialID"));
                    String name = req.getParameter("update_itemMaterialName");

                    ItemMaterial itemMaterial = new ItemMaterial();
                    itemMaterial.setId(id);
                    itemMaterial.setName(name);

                    if (itemMaterialServiceImpl.create(itemMaterial)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemMaterial", itemMaterial);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteItemMaterial"->{
                    System.out.println("Xóa vật liệu sản phẩm");

                    Integer itemMaterialID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (itemMaterialServiceImpl.deleteByID(itemMaterialID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortItemMaterial"->{
                    String searchType=req.getParameter("itemMaterialSearchType");
                    String itemMaterialInputSearch=req.getParameter("itemMaterialInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer itemMaterialID=Integer.parseInt(req.getParameter("itemMaterialInputSearch"));
                            ItemMaterial itemMaterial=itemMaterialServiceImpl.getItemMaterial(itemMaterialID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemMaterial",itemMaterial);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String itemMaterialSortType=req.getParameter("itemMaterialSortType");
                            List<ItemMaterial> itemMaterialList=null;
                            if(itemMaterialSortType.equals("az")){
                                itemMaterialList=itemMaterialServiceImpl.findAllByNameContains(itemMaterialInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemMaterialSortType.equals("za")){
                                itemMaterialList=itemMaterialServiceImpl.findAllByNameContains(itemMaterialInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemMaterialList",itemMaterialList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*ItemType*/
                case "addItemType"->{
                    String name = req.getParameter("add_itemTypeName");

                    ItemType itemType = new ItemType();
                    itemType.setName(name);

                    if (itemTypeServiceImpl.create(itemType)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemType", itemType);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemType"->{
                    int id = Integer.parseInt(req.getParameter("update_itemTypeID"));
                    String name = req.getParameter("update_itemTypeName");

                    ItemType itemType = new ItemType();
                    itemType.setId(id);
                    itemType.setName(name);

                    if (itemTypeServiceImpl.create(itemType)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemType", itemType);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteItemType"->{
                    System.out.println("Xóa loại sản phẩm");

                    Integer itemTypeID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (itemTypeServiceImpl.deleteByID(itemTypeID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortItemType"->{
                    String searchType=req.getParameter("itemTypeSearchType");
                    String itemTypeInputSearch=req.getParameter("itemTypeInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer itemTypeID=Integer.parseInt(req.getParameter("itemTypeInputSearch"));
                            ItemType itemType=itemTypeServiceImpl.getItemType(itemTypeID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemType",itemType);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String itemTypeSortType=req.getParameter("itemTypeSortType");
                            List<ItemType> itemTypeList=null;
                            if(itemTypeSortType.equals("az")){
                                itemTypeList=itemTypeServiceImpl.findAllByNameContains(itemTypeInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemTypeSortType.equals("za")){
                                itemTypeList=itemTypeServiceImpl.findAllByNameContains(itemTypeInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemTypeList",itemTypeList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*OrderDetail*/
                case "addOrderDetail"->{
                    int orderID = Integer.parseInt(req.getParameter("add_orderDetailOrderID"));
                    int itemID = Integer.parseInt(req.getParameter("add_orderDetailItemID"));
                    int amount = Integer.parseInt(req.getParameter("add_orderDetailAmount"));
                    String itemColor = req.getParameter("add_orderDetailItemColor");
                    String itemSize = req.getParameter("add_orderDetailItemSize");
                    double total = Double.parseDouble(req.getParameter("add_orderDetailTotal"));

                    Item item = itemServiceImpl.getItem(itemID);
                    Order order = orderServiceImpl.getOrder(orderID);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setTotal(total);
                    orderDetail.setItem(item);
                    orderDetail.setAmount(amount);
                    orderDetail.setOrder(order);
                    orderDetail.setItem_color(itemColor);
                    orderDetail.setItem_size(itemSize);

                    if (orderDetailServiceImpl.create(orderDetail)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderDetail", orderDetail);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrderDetail"->{
                    int orderDetailID = Integer.parseInt(req.getParameter("update_orderDetailID"));
                    int orderID = Integer.parseInt(req.getParameter("update_orderDetailOrderID"));
                    int itemID = Integer.parseInt(req.getParameter("update_orderDetailItemID"));
                    int amount = Integer.parseInt(req.getParameter("update_orderDetailAmount"));
                    String itemColor = req.getParameter("update_orderDetailItemColor");
                    String itemSize = req.getParameter("update_orderDetailItemSize");
                    double total = Double.parseDouble(req.getParameter("update_orderDetailTotal"));

                    Item item = itemServiceImpl.getItem(itemID);
                    Order order = orderServiceImpl.getOrder(orderID);

                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(orderDetailID);
                    orderDetail.setTotal(total);
                    orderDetail.setItem(item);
                    orderDetail.setAmount(amount);
                    orderDetail.setOrder(order);
                    orderDetail.setItem_color(itemColor);
                    orderDetail.setItem_size(itemSize);

                    if (orderDetailServiceImpl.create(orderDetail)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderDetail", orderDetail);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteOrderDetail"->{
                    System.out.println("Xóa hóa đơn chi tiết");

                    Integer orderDetailID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (orderDetailServiceImpl.deleteByID(orderDetailID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortOrderDetail"->{
                    String searchType=req.getParameter("orderDetailSearchType");
                    String orderDetailInputSearch=req.getParameter("orderDetailInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer orderDetailID=Integer.parseInt(req.getParameter("orderDetailInputSearch"));
                            OrderDetail orderDetail=orderDetailServiceImpl.getOrderDetail(orderDetailID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderDetail",orderDetail);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "orderID"->{
                            String orderDetailSortType=req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList=null;
                            if(orderDetailSortType.equals("az")){
                                orderDetailList=orderDetailServiceImpl.findAllByOrderID(Integer.parseInt(orderDetailInputSearch),"item_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderDetailSortType.equals("za")){
                                orderDetailList=orderDetailServiceImpl.findAllByOrderID(Integer.parseInt(orderDetailInputSearch),"item_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderDetailList",orderDetailList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "lowerPrice"->{
                            String orderDetailSortType=req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList=null;
                            if(orderDetailSortType.equals("az")){
                                orderDetailList=orderDetailServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderDetailInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderDetailSortType.equals("za")){
                                orderDetailList=orderDetailServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderDetailInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderDetailList",orderDetailList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "higherPrice"->{
                            String orderDetailSortType=req.getParameter("orderDetailSortType");
                            List<OrderDetail> orderDetailList=null;
                            if(orderDetailSortType.equals("az")){
                                orderDetailList=orderDetailServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderDetailInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderDetailSortType.equals("za")){
                                orderDetailList=orderDetailServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderDetailInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderDetailList",orderDetailList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Order*/
                case "addOrder"->{
                    int customerID = Integer.parseInt(req.getParameter("add_orderCustomerID"));
                    int discountCardID = Integer.parseInt(req.getParameter("add_orderDiscountCardID"));
                    double total = Double.parseDouble(req.getParameter("add_orderTotal"));
                    String datePurchase = req.getParameter("add_orderDatePurchase");
                    OrderStatus orderStatus=orderStatusServiceImpl.findById(Integer.parseInt(req.getParameter("add_orderOrderStatusID")));
                    String address=req.getParameter("add_orderAddress");
                    String note=req.getParameter("add_orderNote");
                    String email=req.getParameter("add_orderEmail");


                    /*Xử lý định dạng date*/
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date datePurchaseFormatted = null;
                    try {
                        datePurchaseFormatted = inputFormat.parse(datePurchase);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Customer customer = customerServiceImpl.getCustomer(customerID);
                    DiscountCard discountCard = discountCardServiceImpl.getDiscountCard(discountCardID);


                    Order order = new Order();
                    order.setCustomer(customer);
                    order.setTotal(total);
                    order.setDate_purchase(datePurchaseFormatted);
                    order.setDiscountCard(discountCard);
                    order.setOrder_status(orderStatus);
                    order.setNote(note);
                    order.setAddress(address);
                    order.setEmail(email);

                    if (orderServiceImpl.create(order)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("order", order);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrder"->{
                    int orderID=Integer.parseInt(req.getParameter("update_orderID"));
                    int customerID = Integer.parseInt(req.getParameter("update_orderCustomerID"));
                    int discountCardID = Integer.parseInt(req.getParameter("update_orderDiscountCardID"));
                    double total = Double.parseDouble(req.getParameter("update_orderTotal"));
                    String datePurchase = req.getParameter("update_orderDatePurchase");
                    OrderStatus orderStatus=orderStatusServiceImpl.findById(Integer.parseInt(req.getParameter("update_orderOrderStatusID")));
                    String address=req.getParameter("update_orderAddress");
                    String note=req.getParameter("update_orderNote");
                    String email=req.getParameter("update_orderEmail");

                    /*Xử lý định dạng date*/
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date datePurchaseFormatted = null;
                    try {
                        datePurchaseFormatted = inputFormat.parse(datePurchase);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Customer customer = customerServiceImpl.getCustomer(customerID);
                    DiscountCard discountCard = discountCardServiceImpl.getDiscountCard(discountCardID);


                    Order order = new Order();
                    order.setId(orderID);
                    order.setCustomer(customer);
                    order.setTotal(total);
                    order.setDate_purchase(datePurchaseFormatted);
                    order.setDiscountCard(discountCard);
                    order.setOrder_status(orderStatus);
                    order.setNote(note);
                    order.setAddress(address);
                    order.setEmail(email);

                    if (orderServiceImpl.create(order)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("order", order);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteOrder"->{
                    System.out.println("Xóa hóa đơn");

                    Integer orderID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (orderServiceImpl.deleteByID(orderID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortOrder"->{
                    String searchType=req.getParameter("orderSearchType");
                    String orderInputSearch=req.getParameter("orderInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer orderID=Integer.parseInt(req.getParameter("orderInputSearch"));
                            Order order=orderServiceImpl.getOrder(orderID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("order",order);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "customerID"->{
                            String orderSortType=req.getParameter("orderSortType");
                            List<Order> orderList=null;
                            if(orderSortType.equals("az")){
                                orderList=orderServiceImpl.findAllByCustomerID(Integer.parseInt(orderInputSearch),"customer_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderSortType.equals("za")){
                                orderList=orderServiceImpl.findAllByCustomerID(Integer.parseInt(orderInputSearch),"customer_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderList",orderList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "lowerPrice"->{
                            String orderSortType=req.getParameter("orderSortType");
                            List<Order> orderList=null;
                            if(orderSortType.equals("az")){
                                orderList=orderServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderSortType.equals("za")){
                                orderList=orderServiceImpl.findAllByTotalLessThan(Double.parseDouble(orderInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderList",orderList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "higherPrice"->{
                            String orderSortType=req.getParameter("orderSortType");
                            List<Order> orderList=null;
                            if(orderSortType.equals("az")){
                                orderList=orderServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderSortType.equals("za")){
                                orderList=orderServiceImpl.findAllByTotalGreaterThan(Double.parseDouble(orderInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderList",orderList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Permission*/
                case "addPermission"->{
                    int level = Integer.parseInt(req.getParameter("add_permissionLevel"));
                    String name = req.getParameter("add_permissionName");

                    Permission permission = new Permission();
                    permission.setLevel(level);
                    permission.setName(name);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updatePermission"->{
                    int id = Integer.parseInt(req.getParameter("update_permissionID"));
                    String name = req.getParameter("update_permissionName");
                    int level = Integer.parseInt(req.getParameter("update_permissionLevel"));

                    Permission permission = new Permission();
                    permission.setId(id);
                    permission.setLevel(level);
                    permission.setName(name);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deletePermission"->{
                    System.out.println("Xóa quyền tài khoản");

                    Integer permissionID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (permissionServiceImpl.deleteByID(permissionID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortPermission"->{
                    String searchType=req.getParameter("permissionSearchType");
                    String permissionInputSearch=req.getParameter("permissionInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer permissionID=Integer.parseInt(req.getParameter("permissionInputSearch"));
                            Permission permission=permissionServiceImpl.findByID(permissionID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("permission",permission);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String permissionSortType=req.getParameter("permissionSortType");
                            List<Permission> permissionList=null;
                            if(permissionSortType.equals("az")){
                                permissionList=permissionServiceImpl.findAllByNameContains(permissionInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(permissionSortType.equals("za")){
                                permissionList=permissionServiceImpl.findAllByNameContains(permissionInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("permissionList",permissionList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Sale*/
                case "addSale"->{
                    int itemId = Integer.parseInt(req.getParameter("add_saleItemID"));
                    String name = req.getParameter("add_saleName");
                    int onSale = Integer.parseInt(req.getParameter("add_saleOnSale"));
                    int salePercentage = Integer.parseInt(req.getParameter("add_salePercentage"));

                    Item item = itemServiceImpl.getItem(itemId);

                    Sale sale = new Sale();
                    sale.setSale_percentage(salePercentage);
                    sale.setOn_sale(onSale);
                    sale.setItem(item);
                    sale.setName(name);

                    if (saleServiceImpl.create(sale)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("sale", sale);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateSale"->{
                    int id = Integer.parseInt(req.getParameter("update_saleID"));
                    int itemId = Integer.parseInt(req.getParameter("update_saleItemID"));
                    String name = req.getParameter("update_saleName");
                    int onSale = Integer.parseInt(req.getParameter("update_saleOnSale"));
                    int salePercentage = Integer.parseInt(req.getParameter("update_salePercentage"));

                    Item item = itemServiceImpl.getItem(itemId);

                    Sale sale = new Sale();
                    sale.setId(id);
                    sale.setSale_percentage(salePercentage);
                    sale.setOn_sale(onSale);
                    sale.setItem(item);
                    sale.setName(name);

                    if (saleServiceImpl.create(sale)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("sale", sale);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteSale"->{
                    System.out.println("Xóa thẻ khuyến mãi");

                    Integer saleID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (saleServiceImpl.deleteByID(saleID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortSale"->{
                    String searchType=req.getParameter("saleSearchType");
                    String saleInputSearch=req.getParameter("saleInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer saleID=Integer.parseInt(req.getParameter("saleInputSearch"));
                            Sale sale=saleServiceImpl.getSale(saleID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("sale",sale);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String saleSortType=req.getParameter("saleSortType");
                            List<Sale> saleList=null;
                            if(saleSortType.equals("az")){
                                saleList=saleServiceImpl.findAllByNameContains(saleInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(saleSortType.equals("za")){
                                saleList=saleServiceImpl.findAllByNameContains(saleInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("saleList",saleList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*StockItem*/
                case "addStockItem"->{
                    int itemId = Integer.parseInt(req.getParameter("itemID"));
                    String color = req.getParameter("color");
                    String size = req.getParameter("size");
                    int amount = Integer.parseInt(req.getParameter("amount"));

                    Item item = itemServiceImpl.getItem(itemId);

                    StockItem stockItem = new StockItem();
                    stockItem.setAmount(amount);
                    stockItem.setSize(size);
                    stockItem.setColor(color);
                    stockItem.setItem(item);

                    if (stockItemServiceImpl.create(stockItem)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("stockItem", stockItem);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateStockItem"->{
                    int id = Integer.parseInt(req.getParameter("id"));
                    int itemId = Integer.parseInt(req.getParameter("itemID"));
                    String color = req.getParameter("color");
                    String size = req.getParameter("size");
                    int amount = Integer.parseInt(req.getParameter("amount"));

                    Item item = itemServiceImpl.getItem(itemId);

                    StockItem stockItem = new StockItem();
                    stockItem.setId(id);
                    stockItem.setAmount(amount);
                    stockItem.setSize(size);
                    stockItem.setColor(color);
                    stockItem.setItem(item);

                    if (stockItemServiceImpl.create(stockItem)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("stockItem", stockItem);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteStockItem"->{
                    System.out.println("Xóa thông tin sản phẩm");

                    Integer stockItemID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (stockItemServiceImpl.deleteByID(stockItemID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortStockItem"->{
                    String searchType=req.getParameter("stockItemSearchType");
                    String stockItemInputSearch=req.getParameter("stockItemInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer stockItemID=Integer.parseInt(req.getParameter("stockItemInputSearch"));
                            StockItem stockItem=stockItemServiceImpl.getStockItem(stockItemID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("stockItem",stockItem);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "itemID"->{
                            String stockItemSortType=req.getParameter("stockItemSortType");
                            List<StockItem> stockItemList=null;
                            if(stockItemSortType.equals("az")){
                                stockItemList=stockItemServiceImpl.findAllByItemID(Integer.parseInt(stockItemInputSearch),"item_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(stockItemSortType.equals("za")){
                                stockItemList=stockItemServiceImpl.findAllByItemID(Integer.parseInt(stockItemInputSearch),"item_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("stockItemList",stockItemList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*Item*/
                case "addItem"->{
                    String name = req.getParameter("add_itemName");
                    int itemTypeID = Integer.parseInt(req.getParameter("add_itemTypeID"));
                    int itemMaterialID = Integer.parseInt(req.getParameter("add_itemMaterialID"));
                    int itemCollectionID = Integer.parseInt(req.getParameter("add_itemCollectionID"));
                    int isNew = Integer.parseInt(req.getParameter("add_itemIsNew"));
                    int isHot = Integer.parseInt(req.getParameter("add_itemIsHot"));
                    double price = Double.parseDouble(req.getParameter("add_itemPrice"));
                    int yearProduce = Integer.parseInt(req.getParameter("add_itemYearProduce"));
                    String description=req.getParameter("add_itemDescription");

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
                    item.setDescription(description);

                    if (itemServiceImpl.create(item)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("item", item);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItem"->{
                    int itemID=Integer.parseInt(req.getParameter("update_itemID"));
                    String name = req.getParameter("update_itemName");
                    int itemTypeID = Integer.parseInt(req.getParameter("update_itemTypeID"));
                    int itemMaterialID = Integer.parseInt(req.getParameter("update_itemMaterialID"));
                    int itemCollectionID = Integer.parseInt(req.getParameter("update_itemCollectionID"));
                    int isNew = Integer.parseInt(req.getParameter("update_itemIsNew"));
                    int isHot = Integer.parseInt(req.getParameter("update_itemIsHot"));
                    double price = Double.parseDouble(req.getParameter("update_itemPrice"));
                    int yearProduce = Integer.parseInt(req.getParameter("update_itemYearProduce"));
                    String description=req.getParameter("update_itemDescription");

                    ItemCollection itemCollection = itemCollectionServiceImpl.getItemCollection(itemCollectionID);
                    ItemMaterial itemMaterial = itemMaterialServiceImpl.getItemMaterial(itemMaterialID);
                    ItemType itemType = itemTypeServiceImpl.getItemType(itemTypeID);

                    Item item = new Item();
                    item.setId(itemID);
                    item.setIs_hot(isHot);
                    item.setIs_new(isNew);
                    item.setItemCollection(itemCollection);
                    item.setItemMaterial(itemMaterial);
                    item.setItemType(itemType);
                    item.setName(name);
                    item.setPrice(price);
                    item.setYear_produce(yearProduce);
                    item.setDescription(description);

                    if (itemServiceImpl.create(item)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("item", item);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteItem"->{
                    System.out.println("Xóa hóa đơn chi tiết");

                    Integer itemID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (itemServiceImpl.deleteByID(itemID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortItem"->{
                    String searchType=req.getParameter("itemSearchType");
                    String itemInputSearch=req.getParameter("itemInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer itemID=Integer.parseInt(req.getParameter("itemInputSearch"));
                            Item item=itemServiceImpl.getItem(itemID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("item",item);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "itemColor"->{
                            String itemSortType=req.getParameter("itemSortType");
                            List<Item> itemList=null;
                            if(itemSortType.equals("az")){
                                itemList=itemServiceImpl.findItemListByColor(itemInputSearch,"item_id", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemSortType.equals("za")){
                                itemList=itemServiceImpl.findItemListByColor(itemInputSearch,"item_id", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemList",itemList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "lowerPrice"->{
                            String itemSortType=req.getParameter("itemSortType");
                            List<Item> itemList=null;
                            if(itemSortType.equals("az")){
                                itemList=itemServiceImpl.findAllByPriceLessThan(Double.parseDouble(itemInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemSortType.equals("za")){
                                itemList=itemServiceImpl.findAllByPriceLessThan(Double.parseDouble(itemInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemList",itemList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                        case "higherPrice"->{
                            String itemSortType=req.getParameter("itemSortType");
                            List<Item> itemList=null;
                            if(itemSortType.equals("az")){
                                itemList=itemServiceImpl.findAllByPriceGreaterThan(Double.parseDouble(itemInputSearch),"total", ItemServiceImpl.SortOrder.ASC);
                            }else if(itemSortType.equals("za")){
                                itemList=itemServiceImpl.findAllByPriceGreaterThan(Double.parseDouble(itemInputSearch),"total", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("itemList",itemList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

                /*OrderStatus*/
                case "addOrderStatus"->{
                    String name = req.getParameter("add_orderStatusName");

                    OrderStatus orderStatus = new OrderStatus();
                    orderStatus.setName(name);

                    if (orderStatusServiceImpl.create(orderStatus)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderStatus", orderStatus);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrderStatus"->{
                    int id = Integer.parseInt(req.getParameter("update_orderStatusID"));
                    String name = req.getParameter("update_orderStatusName");

                    OrderStatus orderStatus = new OrderStatus();
                    orderStatus.setId(id);
                    orderStatus.setName(name);

                    if (orderStatusServiceImpl.create(orderStatus)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderStatus", orderStatus);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        ObjectMapper mapper=new ObjectMapper();
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteOrderStatus"->{
                    System.out.println("Xóa vật liệu sản phẩm");

                    Integer orderStatusID=Integer.parseInt(req.getParameter("id"));
                    ObjectMapper mapper=new ObjectMapper();

                    if (orderStatusServiceImpl.deleteByID(orderStatusID)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "searchAndSortOrderStatus"->{
                    String searchType=req.getParameter("orderStatusSearchType");
                    String orderStatusInputSearch=req.getParameter("orderStatusInputSearch");
                    switch (searchType){
                        case "noData"->{

                        }
                        case "id"->{
                            Integer orderStatusID=Integer.parseInt(req.getParameter("orderStatusInputSearch"));
                            OrderStatus orderStatus=orderStatusServiceImpl.findById(orderStatusID);

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderStatus",orderStatus);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);

                        }
                        case "name"->{
                            String orderStatusSortType=req.getParameter("orderStatusSortType");
                            List<OrderStatus> orderStatusList=null;
                            if(orderStatusSortType.equals("az")){
                                orderStatusList=orderStatusServiceImpl.findAllByNameContains(orderStatusInputSearch,"name", ItemServiceImpl.SortOrder.ASC);
                            }else if(orderStatusSortType.equals("za")){
                                orderStatusList=orderStatusServiceImpl.findAllByNameContains(orderStatusInputSearch,"name", ItemServiceImpl.SortOrder.DESC);
                            }

                            ObjectMapper mapper=new ObjectMapper();
                            Map<String, Object> responseData = new HashMap<>();
                            responseData.put("success", true);
                            responseData.put("orderStatusList",orderStatusList);

                            // Convert the map to JSON
                            String json = mapper.writeValueAsString(responseData);

                            resp.setContentType("application/json");
                            resp.setCharacterEncoding("UTF-8");
                            resp.getWriter().write(json);
                        }
                    }

                }

            }

        }catch (Exception er){
            er.printStackTrace();
        }
    }
}
