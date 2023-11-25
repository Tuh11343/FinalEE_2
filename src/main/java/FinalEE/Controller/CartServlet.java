package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private CartServiceImpl cartServiceImpl;


    /*Hash Map*/
    private Map<Integer,Sale> saleMap;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "orderClick" -> {
                System.out.println("Ban da dat hang");

                double orderTotal=0.0;
                Customer signInCustomer=null;
                Integer discountCardID=null;
                Integer signInAccountID=null;

                List<Cookie> cookieList= List.of(req.getCookies());
                for(Cookie cookie: cookieList){
                    if(cookie.getName().equals("signInAccountID")){
                        signInAccountID=Integer.parseInt(cookie.getValue());
                        Account account=accountServiceImpl.findByID(signInAccountID);
                        signInCustomer=account.getCustomer();
                    }
                }

                if(req.getParameter("customerDiscountCardID")!=null){
                    discountCardID=Integer.parseInt(req.getParameter("customerDiscountCardID"));
                }
                
                DiscountCard discountCard=discountCardServiceImpl.getDiscountCard(discountCardID);

                if(signInCustomer==null){
                    signInCustomer=new Customer();
                    signInCustomer.setName(req.getParameter("orderCustomerName"));
                    signInCustomer.setPhone_number(req.getParameter("orderCustomerPhoneNumber"));
                    customerServiceImpl.create(signInCustomer);
                }

                /*Create Oder*/
                ItemOrder order=new ItemOrder();
                order.setCustomer(signInCustomer);
                order.setTotal(orderTotal);
                order.setDate_purchase(new Date());
                order.setDiscountCard(discountCard);
                order.setNote(req.getParameter("orderNote"));
                order.setOrder_status(0);
                order.setAddress(req.getParameter("orderAddress"));
                orderServiceImpl.create(order);

                if(order.getId()==null){
                    return;
                }

                /*Create Order Detail*/
                List<Cart> cartList = cartServiceImpl.findByCustomerID(signInCustomer.getId());
                for (Cart cart : cartList){

                    OrderDetail orderDetail=new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setItem_size(cart.getStockItem().getSize());
                    orderDetail.setItem_color(cart.getStockItem().getColor());
                    orderDetail.setAmount(cart.getAmount());
                    orderDetail.setTotal(calculateOrderDetailTotal(cart));
                    orderDetail.setItem(cart.getStockItem().getItem());

                    orderDetailServiceImpl.create(orderDetail);

                    orderTotal+=orderDetail.getTotal();
                }

                //Update Order Total
                order.setTotal(orderTotal);
                orderServiceImpl.create(order);

                /*Remove Cart*/
                cartServiceImpl.deleteAllByCustomerID(signInCustomer.getId());

                req.getRequestDispatcher("Views/User/Cart.jsp").forward(req,resp);

            }
            case "itemDeleteClick" -> {
                System.out.println("Ban da nhan vao nut xoa vat pham");
                //Remove Item From Cart
                Integer deleteCartID=null;
                deleteCartID=Integer.parseInt(req.getParameter("deleteCartID"));
                cartServiceImpl.deleteByID(deleteCartID);

                req.getRequestDispatcher("Views/User/Cart.jsp").forward(req, resp);
            }
            case "addMoreItem" -> {
                resp.sendRedirect("/FinalEE/ItemServlet");
            }
            default -> {
                System.out.println("WTF:"+action);
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();
        HttpSession session = req.getSession();

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
        List<ItemOrder> orderList = orderServiceImpl.getAllOrder();
        List<OrderDetail> orderDetailList = orderDetailServiceImpl.getAllOrderDetail();
        List<ItemType> itemTypeList = itemTypeServiceImpl.getAllItemType();
        List<Permission> permissionList = permissionServiceImpl.getAllPermission();
        List<Sale> saleList = saleServiceImpl.getAllSale();
        List<StockItem> stockItemList = stockItemServiceImpl.getAllStockItem();

        //Init Hash Map
        initHashMap(saleList);

        Integer signInAccountID=null;
        Customer signInCustomer=null;

        List<Cookie> cookieList= List.of(req.getCookies());
        for(Cookie cookie: cookieList){
            if(cookie.getName().equals("signInAccountID")){
                signInAccountID=Integer.parseInt(cookie.getValue());
                Account account=accountServiceImpl.findByID(signInAccountID);
                signInCustomer=account.getCustomer();
            }
        }

        if (signInAccountID!=null) {
            req.setAttribute("signInCustomer", signInCustomer);

            /*Set Customer Discount Card*/
            List<DiscountCard> customerDiscountCardList = discountCardServiceImpl.findByCustomerID(signInCustomer.getId());
            req.setAttribute("customerDiscountCardList", customerDiscountCardList);
        }
        List<Cart> cartList;
        if(signInCustomer==null)
        {
            cartList = cartServiceImpl.findByCustomerID(null);
        }else{
            cartList=cartServiceImpl.findByCustomerID(signInCustomer.getId());
        }
        /*Set Order Total*/
        calculateOrderTotal(cartList, req);

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
        req.setAttribute("cartList", cartList);

        req.getRequestDispatcher("/Views/User/Cart.jsp").forward(req, resp);
    }

    private void calculateOrderTotal(List<Cart> cartList, HttpServletRequest req) {
        double total = 0.0;
        for (Cart cart : cartList) {
            total+=calculateOrderDetailTotal(cart);
        }
        System.out.println("Tong gia tien:" + total);
        req.setAttribute("orderTotal", total);
    }

    private double calculateOrderDetailTotal(Cart cart){
        double total=0.0;
        Sale sale=saleMap.get(cart.getStockItem().getItem().getId());
        if (sale != null) {
            total += cart.getStockItem().getItem().getPrice() * (1 - (sale.getSale_percentage() / 100)) * cart.getAmount();
        } else {
            total += cart.getStockItem().getItem().getPrice() * cart.getAmount();
        }
        return total;
    }

    private void initHashMap(List<Sale> saleList){
        saleMap = new HashMap<>();
        for (Sale sale : saleList) {
            saleMap.put(sale.getItem().getId(), sale);
        }
    }

}
