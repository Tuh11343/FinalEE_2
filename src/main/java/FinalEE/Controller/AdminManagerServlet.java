/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author ADMIN
 */
@Controller
public class AdminManagerServlet extends HttpServlet {

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
        List<ItemOrder> orderList = orderServiceImpl.getAllOrder();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try{
            response.setContentType("text/html;charset=UTF-8");

            String requestedWith = request.getHeader("X-Requested-With");
            if (requestedWith != null && requestedWith.equals("XMLHttpRequest")) {
                String action = request.getParameter("action");

                switch (action) {

                    case "productsSoldByMonth" -> {

                        System.out.println("Products Sold By Month");


                        int month=-1,year=-1;
                        if(request.getParameter("month")!=null&&request.getParameter("year")!=null){
                            month=Integer.parseInt(request.getParameter("month"));
                            year=Integer.parseInt(request.getParameter("year"));
                        }

                        List<Object[]> productsSoldByMonth = statisticServiceIml.productsSoldByMonth(month,year);

                    /*for(Object[] result:totalProductsSoldByMonth){
                        System.out.println(result[0]+" "+result[1]);
                    }*/

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : productsSoldByMonth) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("itemID", object[0]);
                                jsonObject.put("productsSold", object[1]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }
                    case "productsSoldByYear"->{

                        System.out.println("Products Sold By Year");

                        int year=-1;
                        if(request.getParameter("year")!=null){
                            year=Integer.parseInt(request.getParameter("year"));
                        }

                        List<Object[]> productsSoldByYear = statisticServiceIml.productsSoldByYear(year);

                    /*for(Object[] result:totalProductsSoldByMonth){
                        System.out.println(result[0]+" "+result[1]);
                    }*/

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : productsSoldByYear) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("itemID", object[0]);
                                jsonObject.put("productsSold", object[1]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }

                    case "totalProductsSoldByMonth"->{
                        System.out.println("Total Products Sold By Month");

                        int month=-1,year=-1;
                        if(request.getParameter("year")!=null){
                            month=Integer.parseInt(request.getParameter("month"));
                            year=Integer.parseInt(request.getParameter("year"));
                        }

                        List<Object[]> totalProductsSoldByMonth=statisticServiceIml.totalProductsSoldByMonth(month,year);

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : totalProductsSoldByMonth) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("itemID", object[0]);
                                jsonObject.put("productsSold", object[1]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }

                    case "totalProductsSoldByYear"->{
                        System.out.println("Total Products Sold By Year");

                        int year=-1;
                        if(request.getParameter("year")!=null){
                            year=Integer.parseInt(request.getParameter("year"));
                        }

                        List<Object[]> totalProductsSoldByYear=statisticServiceIml.totalProductsSoldByYear(year);

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : totalProductsSoldByYear) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("itemID", object[0]);
                                jsonObject.put("productsSold", object[1]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }

                    case "recentFiveMonthRevenue"->{
                        System.out.println("Recent Five Month Revenue");

                        List<Object[]> recentFiveMonthRevenue=statisticServiceIml.getRecentFiveMonthRevenue();

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : recentFiveMonthRevenue) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("month", object[1]);
                                jsonObject.put("revenue", object[2]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }

                    case "monthRevenue"->{
                        System.out.println("Month Revenue");

                        int month=-1,year=-1;
                        if(request.getParameter("year")!=null){
                            month=Integer.parseInt(request.getParameter("month"));
                            year=Integer.parseInt(request.getParameter("year"));
                        }

                        List<Object[]> totalProductsSoldByMonth=statisticServiceIml.getRevenueByMonth(month,year);

                        JSONArray jsonArray = new JSONArray();

                        try{
                            for (Object[] object : totalProductsSoldByMonth) {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("month", object[1]);
                                jsonObject.put("revenue", object[2]);
                                jsonArray.put(jsonObject);
                            }

                            // Gửi dữ liệu JSON về cho client
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            response.getWriter().write(jsonArray.toString());
                        }catch (Exception er){
                            er.printStackTrace();
                        }
                    }

                    default -> {

                    }
                }

            } else {
                String action = request.getParameter("action");
                System.out.println(action);
                switch (action) {
                    /*Account*/
                    case "account_btnDelete" -> {
                        int accountID = Integer.parseInt(request.getParameter("accountID"));
                        if (accountServiceImpl.deleteByID(accountID)) {
                            response.getWriter().println("<script>alert('Xóa tài khoản thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa tài khoản thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "account_btnUpdate" -> {

                        int id = Integer.parseInt(request.getParameter("update_accountID"));
                        String name = request.getParameter("update_accountName");
                        String password = request.getParameter("update_accountPassword");
                        int permissionID = Integer.parseInt(request.getParameter("update_accountPermissionID"));
                        int customerID = Integer.parseInt(request.getParameter("update_accountCustomerID"));

                        Permission permission = permissionServiceImpl.getPermission(permissionID);
                        Customer customer = customerServiceImpl.getCustomer(customerID);

                        Account account = new Account();
                        account.setId(id);
                        account.setName(name);
                        account.setCustomer(customer);
                        account.setPassword(password);
                        account.setPermission(permission);

                        if (accountServiceImpl.create(account)) {
                            response.getWriter().println("<script>alert('Cập nhật tài khoản thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Cập nhật tài khoản thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);

                    }
                    case "account_btnAdd" -> {

                        String name = request.getParameter("add_accountName");
                        String password = request.getParameter("add_accountPassword");
                        int permissionID = Integer.parseInt(request.getParameter("add_accountPermissionID"));
                        int customerID = Integer.parseInt(request.getParameter("add_accountCustomerID"));

                        Customer customer = customerServiceImpl.getCustomer(customerID);
                        Permission permission = permissionServiceImpl.getPermission(permissionID);

                        Account account = new Account();
                        account.setName(name);
                        account.setCustomer(customer);
                        account.setPassword(password);
                        account.setPermission(permission);

                        if (accountServiceImpl.create(account)) {
                            response.getWriter().println("<script>alert('Thêm tài khoản thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Thêm tài khoản thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

//            Item Handle
                    case "item_btnAdd" -> {

                        String name = request.getParameter("add_itemName");
                        int itemTypeID = Integer.parseInt(request.getParameter("add_itemTypeID"));
                        int itemMaterialID = Integer.parseInt(request.getParameter("add_itemMaterialID"));
                        int itemCollectionID = Integer.parseInt(request.getParameter("add_itemCollectionID"));
                        int isNew = Integer.parseInt(request.getParameter("add_itemIsNew"));
                        int isHot = Integer.parseInt(request.getParameter("add_itemIsHot"));
                        double price = Double.parseDouble(request.getParameter("add_itemPrice"));
                        int yearProduce = Integer.parseInt(request.getParameter("add_itemYearProduce"));

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
                            response.getWriter().println("<script>alert('Thêm sản phẩm thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Thêm sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "item_btnUpdate" -> {

                        int id = Integer.parseInt(request.getParameter("update_itemID"));
                        String name = request.getParameter("update_itemName");
                        int itemTypeID = Integer.parseInt(request.getParameter("update_itemTypeID"));
                        int itemMaterialID = Integer.parseInt(request.getParameter("update_itemMaterialID"));
                        int itemCollectionID = Integer.parseInt(request.getParameter("update_itemCollectionID"));
                        int isNew = Integer.parseInt(request.getParameter("update_itemIsNew"));
                        int isHot = Integer.parseInt(request.getParameter("update_itemIsHot"));
                        double price = Double.parseDouble(request.getParameter("update_itemPrice"));
                        int yearProduce = Integer.parseInt(request.getParameter("update_itemYearProduce"));

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
                            response.getWriter().println("<script>alert('Cập nhật sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "item_btnDelete" -> {
                        int itemID = Integer.parseInt(request.getParameter("itemID"));
                        if (itemServiceImpl.deleteByID(itemID)) {
                            response.getWriter().println("<script>alert('Xóa sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Customer Handle*/
                    case "customer_btnAdd" -> {

                        System.out.println("Thêm khách hàng");

                        String name = request.getParameter("add_customerName");
                        String phoneNumber = request.getParameter("add_customerPhoneNumber");
                        String email = request.getParameter("add_customerEmail");
                        String address = request.getParameter("add_customerAddress");

                        Customer customer = new Customer();
                        customer.setAddress(address);
                        customer.setEmail(email);
                        customer.setName(name);
                        customer.setPhone_number(phoneNumber);

                        if (customerServiceImpl.create(customer)) {
                            response.getWriter().println("<script>alert('Thêm khách hàng thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Thêm khách hàng thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);

                    }
                    case "customer_btnUpdate" -> {

                        int id = Integer.parseInt(request.getParameter("update_customerID"));
                        String name = request.getParameter("update_customerName");
                        String phoneNumber = request.getParameter("update_customerPhoneNumber");
                        String email = request.getParameter("update_customerEmail");
                        String address = request.getParameter("update_customerAddress");

                        Customer customer = new Customer();
                        customer.setId(id);
                        customer.setAddress(address);
                        customer.setEmail(email);
                        customer.setName(name);
                        customer.setPhone_number(phoneNumber);

                        if (customerServiceImpl.create(customer)) {
                            response.getWriter().println("<script>alert('Cập nhật khách hàng thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Cập nhật khách hàng thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);

                    }
                    case "customer_btnDelete" -> {
                        int customerID = Integer.parseInt(request.getParameter("customerID"));
                        if (customerServiceImpl.deleteByID(customerID)) {
                            response.getWriter().println("<script>alert('Xóa khách hàng thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa khách hàng thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Discount Card*/
                    case "discountCard_btnAdd" -> {

                        int customerID = Integer.parseInt(request.getParameter("add_discountCardID"));
                        String name = request.getParameter("add_discountCardName");
                        int discountPercentage = Integer.parseInt(request.getParameter("add_discountCardDiscountPercentage"));

                        Customer customer = customerServiceImpl.getCustomer(customerID);

                        DiscountCard discountCard = new DiscountCard();
                        discountCard.setCustomer(customer);
                        discountCard.setDiscount_percentage(discountPercentage);
                        discountCard.setName(name);

                        if (discountCardServiceImpl.create(discountCard)) {
                            response.getWriter().println("<script>alert('Thêm khách hàng thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Thêm khách hàng thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);

                    }
                    case "discountCard_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_discountCardID"));
                        int discountPercentage = Integer.parseInt(request.getParameter("update_discountCardDiscountPercentage"));
                        String name = request.getParameter("update_discountCardName");
                        int customerID = Integer.parseInt(request.getParameter("update_discountCardID"));

                        Customer customer = customerServiceImpl.getCustomer(customerID);

                        DiscountCard discountCard = new DiscountCard();
                        discountCard.setId(id);
                        discountCard.setCustomer(customer);
                        discountCard.setDiscount_percentage(discountPercentage);
                        discountCard.setName(name);

                        if (discountCardServiceImpl.create(discountCard)) {
                            response.getWriter().println("<script>alert('Cập nhật thẻ khuyến mãi thành công!');</script>");

                        } else {
                            response.getWriter().println("<script>alert('Cập nhật thẻ khuyến mãi thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "discountCard_btnDelete" -> {
                        int discountCardID = Integer.parseInt(request.getParameter("discountCardID"));
                        if (discountCardServiceImpl.deleteByID(discountCardID)) {
                            response.getWriter().println("<script>alert('Xóa thẻ khuyến mãi thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa thẻ khuyến mãi thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Item Collection*/
                    case "itemCollection_btnAdd" -> {

                        String name = request.getParameter("add_itemCollectionName");

                        ItemCollection itemCollection = new ItemCollection();
                        itemCollection.setName(name);

                        if (itemCollectionServiceImpl.create(itemCollection)) {
                            response.getWriter().println("<script>alert('Thêm bộ sưu tập thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm bộ sưu tập thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemCollection_btnUpdate" -> {

                        int id = Integer.parseInt(request.getParameter("update_itemCollectionID"));
                        String name = request.getParameter("update_itemCollectionName");

                        ItemCollection itemCollection = new ItemCollection();
                        itemCollection.setId(id);
                        itemCollection.setName(name);

                        if (itemCollectionServiceImpl.create(itemCollection)) {
                            response.getWriter().println("<script>alert('Thêm bộ sưu tập thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm bộ sưu tập thất bại!');</script>");
                        }

                    }
                    case "itemCollection_btnDelete" -> {
                        int itemCollectionID = Integer.parseInt(request.getParameter("itemCollectionID"));
                        if (itemCollectionServiceImpl.deleteByID(itemCollectionID)) {
                            response.getWriter().println("<script>alert('Xóa bộ sưu tập thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa bộ sưu tập thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Item Image*/
                    case "itemImage_btnAdd" -> {
                        int itemID = Integer.parseInt(request.getParameter("add_itemImageItemID"));
                        String url = request.getParameter("add_itemImageURL");

                        Item item = itemServiceImpl.getItem(itemID);

                        ItemImage itemImage = new ItemImage();
                        itemImage.setImage_url(url);
                        itemImage.setItem(item);

                        if (itemImageServiceImpl.create(itemImage)) {
                            response.getWriter().println("<script>alert('Thêm ảnh cho sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm ảnh cho sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemImage_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_itemImageID"));
                        int itemID = Integer.parseInt(request.getParameter("update_itemImageItemID"));
                        String url = request.getParameter("update_itemImageURL");

                        Item item = itemServiceImpl.getItem(itemID);

                        ItemImage itemImage = new ItemImage();
                        itemImage.setId(id);
                        itemImage.setImage_url(url);
                        itemImage.setItem(item);

                        if (itemImageServiceImpl.create(itemImage)) {
                            response.getWriter().println("<script>alert('Cập nhật ảnh cho sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật ảnh cho sản phẩm thất bại!');</script>");
                        }
                    }
                    case "itemImage_btnDelete" -> {
                        int itemImageID = Integer.parseInt(request.getParameter("itemImageID"));
                        if (itemImageServiceImpl.deleteByID(itemImageID)) {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Item Material*/
                    case "itemMaterial_btnAdd" -> {
                        String name = request.getParameter("add_itemMaterialName");

                        ItemMaterial itemMaterial = new ItemMaterial();
                        itemMaterial.setName(name);

                        if (itemMaterialServiceImpl.create(itemMaterial)) {
                            response.getWriter().println("<script>alert('Thêm vật liệu thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm vật liệu thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemMaterial_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_itemMaterialID"));
                        String name = request.getParameter("update_itemMaterialName");

                        ItemMaterial itemMaterial = new ItemMaterial();
                        itemMaterial.setId(id);
                        itemMaterial.setName(name);

                        if (itemMaterialServiceImpl.create(itemMaterial)) {
                            response.getWriter().println("<script>alert('Cập nhật vật liệu thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật vật liệu thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemMaterial_btnDelete" -> {
                        int itemMaterialID = Integer.parseInt(request.getParameter("itemMaterialID"));
                        if (itemMaterialServiceImpl.deleteByID(itemMaterialID)) {
                            response.getWriter().println("<script>alert('Xóa vật liệu thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa vật liệu thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Order*/
                    case "oder_btnAdd" -> {
                        int customerID = Integer.parseInt(request.getParameter("add_orderCustomerID"));
                        int discountCardID = Integer.parseInt(request.getParameter("add_orderDiscountCardID"));
                        double total = Double.parseDouble(request.getParameter("add_orderTotal"));
                        String datePurchase = request.getParameter("add_orderDatePurchase");

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


                        ItemOrder order = new ItemOrder();
                        order.setCustomer(customer);
                        order.setTotal(total);
                        order.setDate_purchase(datePurchaseFormatted);
                        order.setDiscountCard(discountCard);

                        if (orderServiceImpl.create(order)) {
                            response.getWriter().println("<script>alert('Thêm hóa đơn thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm hóa đơn thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "order_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_orderID"));
                        int customerID = Integer.parseInt(request.getParameter("update_orderCustomerID"));
                        int discountCardID = Integer.parseInt(request.getParameter("update_orderDiscountCardID"));
                        double total = Double.parseDouble(request.getParameter("update_orderTotal"));
                        String datePurchase = request.getParameter("update_orderDatePurchase");

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

                        ItemOrder order = new ItemOrder();
                        order.setId(id);
                        order.setCustomer(customer);
                        order.setTotal(total);
                        order.setDate_purchase(datePurchaseFormatted);
                        order.setDiscountCard(discountCard);

                        if (orderServiceImpl.create(order)) {
                            response.getWriter().println("<script>alert('Cập nhật hóa đơn thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật hóa đơn thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "order_btnDelete" -> {
                        int orderID = Integer.parseInt(request.getParameter("orderID"));
                        if (orderServiceImpl.deleteByID(orderID)) {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Order Detail*/
                    case "orderDetail_btnAdd" -> {
                        int orderID = Integer.parseInt(request.getParameter("add_orderDetailOrderID"));
                        int itemID = Integer.parseInt(request.getParameter("add_orderDetailItemID"));
                        int amount = Integer.parseInt(request.getParameter("add_orderDetailAmount"));
                        String itemColor = request.getParameter("add_orderDetailItemColor");
                        String itemSize = request.getParameter("add_orderDetailItemSize");
                        double total = Double.parseDouble(request.getParameter("add_orderDetailTotal"));

                        Item item = itemServiceImpl.getItem(itemID);
                        ItemOrder order = orderServiceImpl.getOrder(orderID);

                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setTotal(total);
                        orderDetail.setItem(item);
                        orderDetail.setAmount(amount);
                        orderDetail.setOrder(order);
                        orderDetail.setItem_color(itemColor);
                        orderDetail.setItem_size(itemSize);

                        if (orderDetailServiceImpl.create(orderDetail)) {
                            response.getWriter().println("<script>alert('Thêm chi tiết hóa đơn thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm chi tiết hóa đơn thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "orderDetail_btnUpdate" -> {
                        int orderDetailID = Integer.parseInt(request.getParameter("update_orderDetailID"));
                        int orderID = Integer.parseInt(request.getParameter("update_orderDetailOrderID"));
                        int itemID = Integer.parseInt(request.getParameter("update_orderDetailItemID"));
                        int amount = Integer.parseInt(request.getParameter("update_orderDetailAmount"));
                        String itemColor = request.getParameter("update_orderDetailItemColor");
                        String itemSize = request.getParameter("update_orderDetailItemSize");
                        double total = Double.parseDouble(request.getParameter("update_orderDetailTotal"));

                        Item item = itemServiceImpl.getItem(itemID);
                        ItemOrder order = orderServiceImpl.getOrder(orderID);

                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setId(orderDetailID);
                        orderDetail.setTotal(total);
                        orderDetail.setItem(item);
                        orderDetail.setAmount(amount);
                        orderDetail.setOrder(order);
                        orderDetail.setItem_color(itemColor);
                        orderDetail.setItem_size(itemSize);

                        if (orderDetailServiceImpl.create(orderDetail)) {
                            response.getWriter().println("<script>alert('Cập nhật chi tiết hóa đơn thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật chi tiết hóa đơn thất bại!');</script>");
                        }
                    }
                    case "orderDetail_btnDelete" -> {
                        int orderDetailID = Integer.parseInt(request.getParameter("orderDetailID"));
                        if (orderDetailServiceImpl.deleteByID(orderDetailID)) {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa ảnh của sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Item Type*/
                    case "itemType_btnAdd" -> {
                        String name = request.getParameter("add_itemTypeName");

                        ItemType itemType = new ItemType();
                        itemType.setName(name);

                        if (itemTypeServiceImpl.create(itemType)) {
                            response.getWriter().println("<script>alert('Thêm loại sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm loại sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemType_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_itemTypeID"));
                        String name = request.getParameter("update_itemTypeName");

                        ItemType itemType = new ItemType();
                        itemType.setId(id);
                        itemType.setName(name);

                        if (itemTypeServiceImpl.create(itemType)) {
                            response.getWriter().println("<script>alert('Cập nhật loại sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật loại sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "itemType_btnDelete" -> {
                        int itemTypeID = Integer.parseInt(request.getParameter("itemTypeID"));
                        if (itemTypeServiceImpl.deleteByID(itemTypeID)) {
                            response.getWriter().println("<script>alert('Xóa loại sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa loại sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Permission*/
                    case "permission_btnAdd" -> {
                        int level = Integer.parseInt(request.getParameter("add_permissionLevel"));
                        String name = request.getParameter("add_permissionName");

                        Permission permission = new Permission();
                        permission.setLevel(level);
                        permission.setName(name);

                        if (permissionServiceImpl.create(permission)) {
                            response.getWriter().println("<script>alert('Thêm quyền thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm quyền thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "permission_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_permissionID"));
                        String name = request.getParameter("update_permissionName");
                        int level = Integer.parseInt(request.getParameter("update_permissionLevel"));

                        Permission permission = new Permission();
                        permission.setId(id);
                        permission.setLevel(level);
                        permission.setName(name);

                        if (permissionServiceImpl.create(permission)) {
                            response.getWriter().println("<script>alert('Cập nhật quyền thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật quyền thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "permission_btnDelete" -> {
                        int permissionID = Integer.parseInt(request.getParameter("permissionID"));
                        if (permissionServiceImpl.deleteByID(permissionID)) {
                            response.getWriter().println("<script>alert('Xóa vật liệu thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa vật liệu thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Sale*/
                    case "sale_btnAdd" -> {
                        int itemId = Integer.parseInt(request.getParameter("add_saleItemID"));
                        String name = request.getParameter("add_saleName");
                        int onSale = Integer.parseInt(request.getParameter("add_saleOnSale"));
                        int salePercentage = Integer.parseInt(request.getParameter("add_salePercentage"));

                        Item item = itemServiceImpl.getItem(itemId);

                        Sale sale = new Sale();
                        sale.setSale_percentage(salePercentage);
                        sale.setOn_sale(onSale);
                        sale.setItem(item);
                        sale.setName(name);

                        if (saleServiceImpl.create(sale)) {
                            response.getWriter().println("<script>alert('Thêm thẻ khuyến mãi thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm thẻ khuyến mãi thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "sale_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_saleID"));
                        int itemId = Integer.parseInt(request.getParameter("update_saleItemID"));
                        String name = request.getParameter("update_saleName");
                        int onSale = Integer.parseInt(request.getParameter("update_saleOnSale"));
                        int salePercentage = Integer.parseInt(request.getParameter("update_salePercentage"));

                        Item item = itemServiceImpl.getItem(itemId);

                        Sale sale = new Sale();
                        sale.setId(id);
                        sale.setSale_percentage(salePercentage);
                        sale.setOn_sale(onSale);
                        sale.setItem(item);
                        sale.setName(name);

                        if (saleServiceImpl.create(sale)) {
                            response.getWriter().println("<script>alert('Cập nhật thẻ khuyến mãi thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật thẻ khuyến mãi thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "sale_btnDelete" -> {
                        int saleID = Integer.parseInt(request.getParameter("saleID"));
                        if (saleServiceImpl.deleteByID(saleID)) {
                            response.getWriter().println("<script>alert('Xóa thẻ khuyến mãi thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa thẻ khuyến mãi thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    /*Stock Item*/
                    case "stockItem_btnAdd" -> {
                        int itemId = Integer.parseInt(request.getParameter("add_stockItemItemID"));
                        String color = request.getParameter("add_stockItemColor");
                        String size = request.getParameter("add_stockItemSize");
                        int amount = Integer.parseInt(request.getParameter("add_stockItemAmount"));

                        Item item = itemServiceImpl.getItem(itemId);

                        StockItem stockItem = new StockItem();
                        stockItem.setAmount(amount);
                        stockItem.setSize(size);
                        stockItem.setColor(color);
                        stockItem.setItem(item);

                        if (stockItemServiceImpl.create(stockItem)) {
                            response.getWriter().println("<script>alert('Thêm hàng hóa sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Thêm hàng hóa sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "stockItem_btnUpdate" -> {
                        int id = Integer.parseInt(request.getParameter("update_stockItemID"));
                        int itemId = Integer.parseInt(request.getParameter("update_stockItemItemID"));
                        String color = request.getParameter("update_stockItemColor");
                        String size = request.getParameter("update_stockItemSize");
                        int amount = Integer.parseInt(request.getParameter("update_stockItemAmount"));

                        Item item = itemServiceImpl.getItem(itemId);

                        StockItem stockItem = new StockItem();
                        stockItem.setId(id);
                        stockItem.setAmount(amount);
                        stockItem.setSize(size);
                        stockItem.setColor(color);
                        stockItem.setItem(item);

                        if (stockItemServiceImpl.create(stockItem)) {
                            response.getWriter().println("<script>alert('Cập nhật hàng hóa sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Cập nhật hàng hóa sản phẩm thất bại!');</script>");
                        }

                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }
                    case "stockItem_btnDelete" -> {
                        int stockItemID = Integer.parseInt(request.getParameter("stockItemID"));
                        if (stockItemServiceImpl.deleteByID(stockItemID)) {
                            response.getWriter().println("<script>alert('Xóa hàng hóa sản phẩm thành công!');</script>");
                        } else {
                            response.getWriter().println("<script>alert('Xóa hàng hóa sản phẩm thất bại!');</script>");
                        }
                        request.getRequestDispatcher("Views/Admin/QuanLy.jsp").forward(request, response);
                    }

                    default -> System.out.println("Khong ton tai action trong AdminManagerServlet");

                }
            }
        }catch (Exception er){
            er.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
