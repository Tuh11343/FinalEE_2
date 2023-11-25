package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.ServiceImpl.*;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.io.PrintWriter;


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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            ServletContext servletContext = getServletContext();
            HttpSession session=req.getSession();

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

            String requestedWith = req.getHeader("X-Requested-With");
            if (requestedWith != null && requestedWith.equals("XMLHttpRequest")){
                String action=req.getParameter("action");

                resp.setContentType("application/json");
                PrintWriter out = resp.getWriter();
                JSONObject jsonResponse=new JSONObject();
                switch (action){

                    case "signIn"->{
                        signInHandle(req, jsonResponse, out);
                    }
                    case "signUp"->{
                        signUpHandle(req, jsonResponse, out);

                    }
                    default -> {

                    }
                }

            }else{
                System.out.println("Action");
                String action=req.getParameter("action");
                switch (action){
                    case "homeClick"->{
                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }
                    case "itemCollectionClick"->{
                        int itemCollectionID=Integer.parseInt(req.getParameter("itemCollectionID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemCollectionID=" + itemCollectionID+"&currentPage="+1);
                    }
                    case "itemTypeClick"->{
                        int itemTypeID=Integer.parseInt(req.getParameter("itemTypeID"));
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?itemTypeID=" + itemTypeID);
                    }
                    case "cartClick"->{
                        resp.sendRedirect("/FinalEE/CartServlet");
                    }
                    case "btnSearchClick"->{
                        String searchInput=req.getParameter("searchInput");
                        resp.sendRedirect("/FinalEE/ItemSearchServlet?searchInput=" + searchInput);
                    }
                    case "accountClick"->{
                        resp.sendRedirect("/FinalEE/ProfileUserServlet");
                    }
                    case "orderClick"->{
                        resp.sendRedirect("/FinalEE/CartServlet");
                    }
                    case "signOutClick"->{
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
        }catch (Exception er){
            System.out.println(er.toString());
        }



    }

    private void signUpHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) throws JSONException {
        String name= req.getParameter("customerName");
        String phoneNumber= req.getParameter("phoneNumber");
        String email= req.getParameter("email");
        String address= req.getParameter("address");
        String password= req.getParameter("password");
        String rePassword= req.getParameter("rePassword");

        System.out.println("Name:"+name);

        /*Create Customer*/
        Customer customer=new Customer();
        customer.setPhone_number(phoneNumber);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);
        customerServiceImpl.create(customer);

        if(customer.getId()!=null){
            Account account=new Account();
            account.setPermission(permissionServiceImpl.findByLevel(0));
            account.setCustomer(customer);
            account.setName(email);
            if(password.equals(rePassword)){
                account.setPassword(password);
                accountServiceImpl.create(account);
                jsonResponse.put("success",1);
            }else{
                System.out.println("Password not correct");
                jsonResponse.put("passwordIncorrect",1);
            }
        }else{
            System.out.println("There is no customer to create Account");
            jsonResponse.put("systemError",1);
        }
        out.print(jsonResponse);
        out.flush();
        out.close();
    }

    private void signInHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) throws JSONException {
        String name= req.getParameter("signInName");
        String password= req.getParameter("signInPassword");

        Account account=accountServiceImpl.findByNameAndPassword(name,password);
        if(account!=null){
            System.out.println("Found Account");
            jsonResponse.put("success",1);
            jsonResponse.put("accountID",account.getId());
        }else{
            System.out.println("No account founded");
            jsonResponse.put("success",0);
        }
        out.print(jsonResponse);
        out.flush();
        out.close();
    }
}
