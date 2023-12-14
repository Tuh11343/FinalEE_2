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
                    Customer customer=mapper.readValue(req.getParameter("customer"),Customer.class);

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
                    System.out.println("Cập nhật khách hàng");

                    ObjectMapper mapper=new ObjectMapper();
                    Customer customer=mapper.readValue(req.getParameter("customer"),Customer.class);

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
                    System.out.println("Thêm tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Account account=mapper.readValue(req.getParameter("account"),Account.class);

                    if (accountServiceImpl.create(account)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("account", account);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateAccount"->{
                    System.out.println("Cập nhật tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Account account=mapper.readValue(req.getParameter("account"),Account.class);

                    if (accountServiceImpl.create(account)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("account", account);
                        responseData.put("success", true);

                        // Convert the map to JSON
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

                    ObjectMapper mapper=new ObjectMapper();
                    DiscountCard discountCard=mapper.readValue(req.getParameter("discountCard"),DiscountCard.class);

                    if (discountCardServiceImpl.create(discountCard)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("discountCard", discountCard);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateDiscountCard"->{
                    System.out.println("Cập nhật thẻ khuyến mãi");

                    ObjectMapper mapper=new ObjectMapper();
                    DiscountCard discountCard=mapper.readValue(req.getParameter("discountCard"),DiscountCard.class);

                    if (discountCardServiceImpl.create(discountCard)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("discountCard", discountCard);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm bộ sưu tập sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemCollection itemCollection=mapper.readValue(req.getParameter("itemCollection"),ItemCollection.class);

                    if (itemCollectionServiceImpl.create(itemCollection)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemCollection", itemCollection);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemCollection"->{
                    System.out.println("Cập nhật bộ sưu tập sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemCollection itemCollection=mapper.readValue(req.getParameter("itemCollection"),ItemCollection.class);

                    if (itemCollectionServiceImpl.create(itemCollection)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemCollection", itemCollection);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm hình ảnh sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemImage itemImage=mapper.readValue(req.getParameter("itemImage"),ItemImage.class);

                    if (itemImageServiceImpl.create(itemImage)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemImage", itemImage);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemImage"->{
                    System.out.println("Cập nhật hình ảnh sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemImage itemImage=mapper.readValue(req.getParameter("itemImage"),ItemImage.class);

                    if (itemImageServiceImpl.create(itemImage)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemImage", itemImage);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm vật liệu sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemMaterial itemMaterial=mapper.readValue(req.getParameter("itemMaterial"),ItemMaterial.class);

                    if (itemMaterialServiceImpl.create(itemMaterial)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemMaterial", itemMaterial);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemMaterial"->{
                    System.out.println("Cập nhật vật liệu sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemMaterial itemMaterial=mapper.readValue(req.getParameter("itemMaterial"),ItemMaterial.class);

                    if (itemMaterialServiceImpl.create(itemMaterial)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemMaterial", itemMaterial);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm loại sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemType itemType=mapper.readValue(req.getParameter("itemType"),ItemType.class);

                    if (itemTypeServiceImpl.create(itemType)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemType", itemType);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateItemType"->{
                    System.out.println("Cập nhật loại sản phẩm");

                    ObjectMapper mapper=new ObjectMapper();
                    ItemType itemType=mapper.readValue(req.getParameter("itemType"),ItemType.class);

                    if (itemTypeServiceImpl.create(itemType)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("itemType", itemType);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm hóa đơn chi tiết");

                    ObjectMapper mapper=new ObjectMapper();
                    OrderDetail orderDetail=mapper.readValue(req.getParameter("orderDetail"),OrderDetail.class);

                    if (orderDetailServiceImpl.create(orderDetail)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderDetail", orderDetail);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrderDetail"->{
                    System.out.println("Cập nhật hóa đơn chi tiết");

                    ObjectMapper mapper=new ObjectMapper();
                    OrderDetail orderDetail=mapper.readValue(req.getParameter("orderDetail"),OrderDetail.class);

                    if (orderDetailServiceImpl.create(orderDetail)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderDetail", orderDetail);
                        responseData.put("success", true);

                        // Convert the map to JSON
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
                    System.out.println("Thêm hóa đơn");

                    ObjectMapper mapper=new ObjectMapper();
                    Order order=mapper.readValue(req.getParameter("order"),Order.class);

                    if (orderServiceImpl.create(order)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("order", order);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrder"->{
                    System.out.println("Cập nhật hóa đơn");

                    ObjectMapper mapper=new ObjectMapper();
                    Order order=mapper.readValue(req.getParameter("order"),Order.class);

                    if (orderServiceImpl.create(order)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("order", order);
                        responseData.put("success", true);

                        // Convert the map to JSON
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

                /*OrderStatus*/
                case "addOrderStatus"->{
                    System.out.println("Thêm tình trạng đơn hàng");

                    ObjectMapper mapper=new ObjectMapper();
                    OrderStatus orderStatus=mapper.readValue(req.getParameter("orderStatus"),OrderStatus.class);

                    if (orderStatusServiceImpl.create(orderStatus)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderStatus", orderStatus);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updateOrderStatus"->{
                    System.out.println("Cập nhật tình trạng đơn hàng");

                    ObjectMapper mapper=new ObjectMapper();
                    OrderStatus orderStatus=mapper.readValue(req.getParameter("orderStatus"),OrderStatus.class);

                    if (orderStatusServiceImpl.create(orderStatus)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("orderStatus", orderStatus);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "deleteOrderStatus"->{
                    System.out.println("Xóa tình trạng đơn hàng");

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

                /*Permission*/
                case "addPermission"->{
                    System.out.println("Thêm quyền tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Permission permission=mapper.readValue(req.getParameter("permission"),Permission.class);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updatePermission"->{
                    System.out.println("Cập nhật quyền tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Permission permission=mapper.readValue(req.getParameter("permission"),Permission.class);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
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

                /*Permission*//*
                case "addPermission"->{
                    System.out.println("Thêm quyền tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Permission permission=mapper.readValue(req.getParameter("permission"),Permission.class);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
                        String json = mapper.writeValueAsString(responseData);

                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        resp.getWriter().write(json);

                    }
                }
                case "updatePermission"->{
                    System.out.println("Cập nhật quyền tài khoản");

                    ObjectMapper mapper=new ObjectMapper();
                    Permission permission=mapper.readValue(req.getParameter("permission"),Permission.class);

                    if (permissionServiceImpl.create(permission)) {

                        Map<String, Object> responseData = new HashMap<>();
                        responseData.put("permission", permission);
                        responseData.put("success", true);

                        // Convert the map to JSON
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

                }*/

            }

        }catch (Exception er){
            er.printStackTrace();
        }
    }
}
