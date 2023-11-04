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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    private CartServiceImpl cartServiceImpl;

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
        cartServiceImpl=webApplicationContext.getBean(CartServiceImpl.class);

        ServletContext servletContext=getServletContext();
        servletContext.setAttribute("accountServiceImpl",accountServiceImpl);
        servletContext.setAttribute("customerServiceImpl",customerServiceImpl);
        servletContext.setAttribute("discountCardServiceImpl",discountCardServiceImpl);
        servletContext.setAttribute("itemServiceImpl",itemServiceImpl);
        servletContext.setAttribute("itemCollectionServiceImpl",itemCollectionServiceImpl);
        servletContext.setAttribute("itemImageServiceImpl",itemImageServiceImpl);
        servletContext.setAttribute("itemMaterialServiceImpl",itemMaterialServiceImpl);
        servletContext.setAttribute("orderServiceImpl",orderServiceImpl);
        servletContext.setAttribute("orderDetailServiceImpl",orderDetailServiceImpl);
        servletContext.setAttribute("itemTypeServiceImpl",itemTypeServiceImpl);
        servletContext.setAttribute("permissionServiceImpl",permissionServiceImpl);
        servletContext.setAttribute("saleServiceImpl",saleServiceImpl);
        servletContext.setAttribute("stockItemServiceImpl",stockItemServiceImpl);
        servletContext.setAttribute("cartServiceImpl",cartServiceImpl);

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


        req.getRequestDispatcher("Views/User/Test_Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action=request.getParameter("action");
        HttpSession session=request.getSession();
        switch(action){
            case "itemClick"->{
                int itemClickID=Integer.parseInt(request.getParameter("itemClickID"));
                response.sendRedirect("/FinalEE/ItemDetailServlet?itemClickID="+itemClickID);
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
