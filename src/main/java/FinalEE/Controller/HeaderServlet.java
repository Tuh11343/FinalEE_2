package FinalEE.Controller;

import FinalEE.Entity.*;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.PrintWriter;
import java.util.*;


public class HeaderServlet extends HttpServlet {

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
    private OrderStatusServiceImpl orderStatusServiceImpl;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
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
            orderStatusServiceImpl = (OrderStatusServiceImpl) servletContext.getAttribute("orderStatusServiceImpl");

            String requestedWith = req.getHeader("X-Requested-With");
            String action = req.getParameter("action");
            if (requestedWith != null && requestedWith.equals("XMLHttpRequest")) {

                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                JSONObject jsonResponse = new JSONObject();
                switch (action) {

                    case "signIn" -> {
                        signInHandle(req, jsonResponse, out);
                    }
                    case "signUp" -> {
                        signUpHandle(req, jsonResponse, out);
                    }
                    case "order" -> {
                        orderHandle(req, jsonResponse, out);
                    }
                    default -> {

                    }
                }

            } else {
                switch (action) {
                    case "homeClick" -> {
                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }
                    case "itemCollectionClick" -> {
                        int itemCollectionID = Integer.parseInt(req.getParameter("itemCollectionID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemCollectionID=" + itemCollectionID + "&currentPage=" + 1 + "&sort=az");
                    }
                    case "itemTypeClick" -> {
                        int itemTypeID = Integer.parseInt(req.getParameter("itemTypeID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemTypeID=" + itemTypeID + "&sort=az");
                    }
                    case "cartClick" -> {
                        resp.sendRedirect("/FinalEE/CartServlet");
                    }
                    case "btnSearchClick" -> {
                        String searchInput = req.getParameter("searchInput");
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?searchInput=" + searchInput + "&sort=az");
                    }
                    case "accountClick" -> {
                        resp.sendRedirect("/FinalEE/ProfileUserServlet");
                    }
                    case "orderClick" -> {
                        resp.sendRedirect("/FinalEE/CartServlet");
                    }
                    case "signOutClick" -> {
                        Cookie[] cookies = req.getCookies();
                        Cookie signInAccountIDCookie = null;
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("signInAccountID")) {
                                signInAccountIDCookie = cookie;
                                break;
                            }
                        }

                        // Đặt thời hạn tồn tại của cookie thành 0
                        if (signInAccountIDCookie != null) {
                            signInAccountIDCookie.setMaxAge(0);
                            signInAccountIDCookie.setPath("/");
                            resp.addCookie(signInAccountIDCookie);
                        }

                        // Chuyển hướng người dùng đến trang chủ
                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }

                    default -> {

                    }
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
        }


    }

    private void orderHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) {

        try {
            Customer signInCustomer = null;
            Integer discountCardID = null;
            Integer signInAccountID;
            List<Cookie> cookieList = List.of(req.getCookies());
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals("signInAccountID")) {
                    signInAccountID = Integer.parseInt(cookie.getValue());
                    Account account = accountServiceImpl.findByID(signInAccountID);
                    signInCustomer = account.getCustomer();
                }
            }

            List<Cart> cartList;
            if(signInCustomer!=null){
                cartList=cartServiceImpl.findByCustomerID(signInCustomer.getId());
            }else{
                ObjectMapper mapper = new ObjectMapper();
                cartList = mapper.readValue(req.getParameter("cartList"), new TypeReference<>() {});
            }

            if (req.getParameter("discountCardID") != null && !req.getParameter("discountCardID").isBlank()) {
                discountCardID = Integer.parseInt(req.getParameter("discountCardID"));
            }
            DiscountCard discountCard = discountCardServiceImpl.getDiscountCard(discountCardID);

            double orderTotal = 0f;
            /*Create Oder*/
            Order order = new Order();
            if(signInCustomer!=null)
                order.setCustomer(signInCustomer);
            else
                order.setCustomer(null);
            order.setTotal(orderTotal);
            order.setEmail(req.getParameter("email"));
            order.setDate_purchase(new Date());
            order.setDiscountCard(discountCard);
            order.setNote(req.getParameter("note"));
            order.setOrder_status(orderStatusServiceImpl.defaultOrder());
            order.setAddress(req.getParameter("address"));
            orderServiceImpl.create(order);

            if (order.getId() == null) {
                System.out.println("Co loi xay ra trong viec tao order");
                return;
            }

            /*Create Order Detail*/
            for (Cart cart : cartList) {

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setAmount(cart.getAmount());
                orderDetail.setTotal(calculateOrderDetailTotal(cart));
                orderDetail.setStockItem(cart.getStockItem());

                orderDetailServiceImpl.create(orderDetail);

                orderTotal += orderDetail.getTotal();
            }

            if(signInCustomer!=null){
                cartServiceImpl.deleteAllByCustomerID(signInCustomer.getId());
            }

            //Update Order Total
            if (discountCard != null) {
                order.setTotal(calculateOrderTotal(orderTotal, discountCard));
            } else {
                order.setTotal(orderTotal);
            }
            orderServiceImpl.create(order);

            sendConfirmOrderEmail(order, cartList);

            jsonResponse.put("success", 1);
            out.print(jsonResponse);
            out.flush();
            out.close();
        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    private void signUpHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) throws JSONException {
        try {
            String name = req.getParameter("customerName");
            String phoneNumber = req.getParameter("phoneNumber");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            String password = req.getParameter("password");
            String rePassword = req.getParameter("rePassword");

            Customer customer = new Customer();
            customer.setPhone_number(phoneNumber);
            customer.setName(name);
            customer.setAddress(address);
            customer.setEmail(email);

            Account account = new Account();
            account.setPermission(permissionServiceImpl.findByLevel(0));
            account.setCustomer(customer);
            account.setName(email);
            if (password.equals(rePassword)) {
                account.setPassword(password);
                jsonResponse.put("success", 1);
                sendAccountConfirmEmail(customer, account);
            } else {
                System.out.println("Password not correct");
                jsonResponse.put("passwordIncorrect", 1);
            }

            out.print(jsonResponse);
            out.flush();
            out.close();
        } catch (
                Exception er) {
            er.printStackTrace();
        }

    }

    private void signInHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out){
        try {
            String name = req.getParameter("signInName");
            String password = req.getParameter("signInPassword");

            Account account = accountServiceImpl.findByNameAndPassword(name, password);
            if (account != null) {
                ObjectMapper mapper = new ObjectMapper();
                if (req.getParameter("cartList") != null && !req.getParameter("cartList").isBlank()) {
                    List<Cart> cartList = mapper.readValue(req.getParameter("cartList"), new TypeReference<>() {});
                    if(cartList!=null){
                        for (Cart cart : cartList) {
                            cart.setCustomer(account.getCustomer());
                            cartServiceImpl.create(cart);
                            jsonResponse.put("deleteLocalCart", true);
                        }
                    }
                }

                jsonResponse.put("success", 1);
                jsonResponse.put("accountID", account.getId());
                jsonResponse.put("accountPermission", account.getPermission().getLevel());

            } else {
                System.out.println("No account founded");
                jsonResponse.put("success", 0);
            }
            out.print(jsonResponse);
            out.flush();
            out.close();
        } catch (Exception er) {
            er.printStackTrace();
        }
    }


    private void sendAccountConfirmEmail(Customer customer, Account account) {
        try {

            Map<String, Object> keyValue = new HashMap<>();
            keyValue.put("phoneNumber", customer.getPhone_number());
            keyValue.put("name", customer.getName());
            keyValue.put("address", customer.getAddress());
            keyValue.put("email", customer.getEmail());
            keyValue.put("accountName", account.getName());
            keyValue.put("accountPassword", account.getPassword());
            keyValue.put("action", "signUp");

            MailServiceImpl mailServiceImpl = new MailServiceImpl();
            String activationLink = mailServiceImpl.mapToJSON(keyValue);

            String htmlMsg = "Xin chào! Để xác thực tài khoản vui lòng nhấp vào <a href='" + activationLink + "'>đường link xác nhận</a>";
            mailServiceImpl.sendMail(keyValue, account.getName(), htmlMsg, "Thư xác nhận tài khoản");

        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    private void sendConfirmOrderEmail(Order order, List<Cart> cartList) {
        try {
            Map<String, Object> keyValue = new HashMap<>();
            keyValue.put("email", order.getEmail());
            keyValue.put("action", "orderConfirm");
            keyValue.put("orderID", order.getId());

            MailServiceImpl mailServiceImpl = new MailServiceImpl();
            String activationLink = mailServiceImpl.mapToJSON(keyValue);
            StringBuilder htmlMsg = new StringBuilder("Đây là đơn hàng của bạn bao gồm:<br>");
            for (Cart cart : cartList) {
                htmlMsg.append("Sản phẩm:").append(cart.getStockItem().getItem().getName()).append("    Số lượng:").append(cart.getAmount()).append("<br>");
            }

            htmlMsg.append("<br><br>Để xác thực đơn hàng vui lòng nhấp vào <a href='").append(activationLink).append("'>đường link xác nhận</a>");
            mailServiceImpl.sendMail(keyValue, order.getEmail(), htmlMsg.toString(), "Thư xác nhận đơn hàng");

        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    private double calculateOrderTotal(double orderTotal, DiscountCard discountCard) {
        double discountPercentage = (double) discountCard.getDiscount_percentage() / 100;
        return orderTotal - (orderTotal * discountPercentage);
    }

    private double calculateOrderDetailTotal(Cart cart) {
        double total = 0.0f;
        Sale sale = cart.getStockItem().getItem().getSale();
        if (sale != null) {
            total += cart.getStockItem().getItem().getPrice() * (1 - (sale.getSale_percentage() / 100)) * cart.getAmount();
        } else {
            total += cart.getStockItem().getItem().getPrice() * cart.getAmount();
        }
        return total;
    }

}
