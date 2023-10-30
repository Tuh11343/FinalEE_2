/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Controller
public class ItemServlet extends HttpServlet {

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


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());

        accountServiceImpl = webApplicationContext.getBean(AccountServiceImpl.class);
        customerServiceImpl = webApplicationContext.getBean(CustomerServiceImpl.class);
        discountCardServiceImpl = webApplicationContext.getBean(DiscountCardServiceImpl.class);
        itemServiceImpl = webApplicationContext.getBean(ItemServiceImpl.class);
        itemCollectionServiceImpl = webApplicationContext.getBean(ItemCollectionServiceImpl.class);
        itemImageServiceImpl = webApplicationContext.getBean(ItemImageServiceImpl.class);
        itemMaterialServiceImpl = webApplicationContext.getBean(ItemMaterialServiceImpl.class);
        orderServiceImpl = webApplicationContext.getBean(OrderServiceImpl.class);
        orderDetailServiceImpl = webApplicationContext.getBean(OrderDetailServiceImpl.class);
        itemTypeServiceImpl = webApplicationContext.getBean(ItemTypeServiceImpl.class);
        permissionServiceImpl = webApplicationContext.getBean(PermissionServiceImpl.class);
        saleServiceImpl = webApplicationContext.getBean(SaleServiceImpl.class);
        stockItemServiceImpl = webApplicationContext.getBean(StockItemServiceImpl.class);

        List<Account> accountList = accountServiceImpl.getAllAccount();
        List<Customer> customerList = customerServiceImpl.getAllCustomer();
        List<DiscountCard> discountCardList = discountCardServiceImpl.getAllDiscountCard();
        List<Item> itemList = itemServiceImpl.getAllItem();
        List<ItemCollection> itemCollectionList = itemCollectionServiceImpl.getAllItemCollection();
        List<ItemImage> itemImageList = itemImageServiceImpl.getAllItemImage();
        List<ItemMaterial> itemMaterialList = itemMaterialServiceImpl.getAllItemMaterial();
        List<ItemOrder> orderList = orderServiceImpl.getAllOrder();
        List<OrderDetail> orderDetailList = orderDetailServiceImpl.getAllOrderDetail();
        List<ItemType> itemTypeList = itemTypeServiceImpl.getAllItemType();
        List<Permission> permissionList = permissionServiceImpl.getAllPermission();
        List<Sale> saleList = saleServiceImpl.getAllSale();
        List<StockItem> stockItemList = stockItemServiceImpl.getAllStockItem();

        /*Set Data List*/
        HttpSession session = req.getSession();
        session.setAttribute("accountList", accountList);
        session.setAttribute("customerList", customerList);
        session.setAttribute("discountCardList", discountCardList);
        session.setAttribute("itemList", itemList);
        session.setAttribute("itemCollectionList", itemCollectionList);
        session.setAttribute("itemImageList", itemImageList);
        session.setAttribute("itemMaterialList", itemMaterialList);
        session.setAttribute("orderList", orderList);
        session.setAttribute("orderDetailList", orderDetailList);
        session.setAttribute("itemTypeList", itemTypeList);
        session.setAttribute("permissionList", permissionList);
        session.setAttribute("saleList", saleList);
        session.setAttribute("stockItemList", stockItemList);


        req.getRequestDispatcher("Views/User/Test_Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action=request.getParameter("action");
        switch(action){
            case "itemCollectionClick" -> {
                System.out.println("Ban da click itemCollection"+request.getParameter("itemType"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);

            }
            case "itemTypeClick"->{
                System.out.println("Ban da click itemType"+request.getParameter("itemType"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);
            }
            case "btnSearchClick" -> {
                System.out.println("Ban da search:"+request.getParameter("btnSearch_data"));
                request.getRequestDispatcher("Views/User/Test_Home.jsp").forward(request, response);

            }
            default -> {
                System.out.println(action);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
