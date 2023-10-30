package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CartServlet extends HttpServlet {

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        switch (action) {
            case "setOrder"->{
                int customerID=Integer.parseInt(req.getParameter("logInCustomerID"));
                int discountCardID=Integer.parseInt(req.getParameter("logInDiscountCardID"));
                double total=0.0;

                ItemOrder order=new ItemOrder();
                order.setCustomer_id(customerID);
                order.setTotal(total);
                order.setDate_purchase(new Date());
                order.setDiscount_card_id(discountCardID);
                orderServiceImpl.create(order);

                int orderID=order.getId();
                System.out.println("OrderID:"+orderID);

                //Handle OrderDetail
                int itemOrderCount=Integer.parseInt(req.getParameter("itemOrderCount"));
                for (int i=0;i<itemOrderCount;i++){
                    int amount=Integer.parseInt(req.getParameter("amount_"+i));
                    int itemID=Integer.parseInt(req.getParameter("itemID_"+i));
                    String size=req.getParameter("itemSize_"+i);
                    String color=req.getParameter("itemColor_"+i);
                    Item item=itemServiceImpl.getItem(itemID);
                    double orderDetailTotal=amount*item.getPrice();

                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setAmount(amount);
                    orderDetail.setTotal(orderDetailTotal);
                    orderDetail.setOrder_id(orderID);
                    orderDetail.setItem_id(itemID);
                    orderDetail.setItem_size(size);
                    orderDetail.setItem_color(color);
                    orderDetailServiceImpl.create(orderDetail);
                }
                double orderTotal=orderDetailServiceImpl.getOrderTotal(orderID);
                order.setTotal(orderTotal);
                orderServiceImpl.create(order);
                req.getRequestDispatcher("").forward(req,resp);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        /*WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());

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

        *//*Set Data List*//*
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
        session.setAttribute("stockItemList", stockItemList);*/


    }
}
