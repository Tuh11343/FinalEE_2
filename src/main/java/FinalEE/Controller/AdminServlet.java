package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.IOUtils;

import java.io.ByteArrayInputStream;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String action=req.getParameter("action");
            switch (action){
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


            }

        }catch (Exception er){
            er.printStackTrace();
        }
    }
}
