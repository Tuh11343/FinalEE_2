package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.Entity.Order;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;


public class MailTest extends HttpServlet {

    private AccountServiceImpl accountServiceImpl;
    private CustomerServiceImpl customerServiceImpl;
    private OrderServiceImpl orderServiceImpl;
    private PermissionServiceImpl permissionServiceImpl;
    private OrderStatusServiceImpl orderStatusServiceImpl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = getServletContext();

        accountServiceImpl = (AccountServiceImpl) servletContext.getAttribute("accountServiceImpl");
        customerServiceImpl = (CustomerServiceImpl) servletContext.getAttribute("customerServiceImpl");
        orderServiceImpl = (OrderServiceImpl) servletContext.getAttribute("orderServiceImpl");
        permissionServiceImpl = (PermissionServiceImpl) servletContext.getAttribute("permissionServiceImpl");
        orderStatusServiceImpl = (OrderStatusServiceImpl) servletContext.getAttribute("orderStatusServiceImpl");

        String encodedData = req.getParameter("data");

        if (encodedData != null) {

            MailServiceImpl mailServiceImpl = new MailServiceImpl();
            Map keyValueData = mailServiceImpl.jsonToMap(encodedData);
            switch (keyValueData.get("action").toString()) {
                case "orderConfirm" -> {
                    if (keyValueData != null) {

                        Order order=orderServiceImpl.findByID(Integer.parseInt(keyValueData.get("orderID").toString()));
                        order.setOrder_status(orderStatusServiceImpl.confirmOrder());
                        orderServiceImpl.create(order);

                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }

                }
                case "signUp" -> {
                    if (keyValueData != null) {
                        String phoneNumber = keyValueData.get("phoneNumber").toString();
                        String customerName = keyValueData.get("name").toString();
                        String address = keyValueData.get("address").toString();
                        String email = keyValueData.get("email").toString();
                        String accountName = keyValueData.get("accountName").toString();
                        String accountPassword = keyValueData.get("accountPassword").toString();

                        Customer customer = new Customer();
                        customer.setEmail(email);
                        customer.setName(customerName);
                        customer.setAddress(address);
                        customer.setPhone_number(phoneNumber);

                        Account account = new Account();
                        account.setPassword(accountPassword);
                        account.setName(accountName);
                        account.setPermission(permissionServiceImpl.findByLevel(0));
                        account.setCustomer(customer);

                        customerServiceImpl.create(customer);
                        accountServiceImpl.create(account);

                        resp.sendRedirect("/FinalEE/ItemServlet");
                    }
                }
                case "forgetPass"->{
                    if(keyValueData!=null){
                        Integer accountID=Integer.parseInt(keyValueData.get("accountID").toString());
                        req.setAttribute("accountID",accountID);
                        req.getRequestDispatcher("/Views/User/passpage.jsp").forward(req, resp);
                    }
                }
            }
        } else {
            System.out.println("Loi");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String action=req.getParameter("action");
            switch (action){
                case "forgetPass"->{
                    PrintWriter out = resp.getWriter();
                    JSONObject jsonResponse = new JSONObject();

                    String password=req.getParameter("password");
                    Integer accountID=Integer.parseInt(req.getParameter("accountID"));
                    Account account=accountServiceImpl.findByID(accountID);
                    if(account!=null){
                        account.setPassword(password);
                        if(accountServiceImpl.create(account)){
                            jsonResponse.put("success", true);
                        }
                    }
                    out.print(jsonResponse);
                    out.flush();
                    out.close();
                }
            }
        }catch (Exception er){
            er.printStackTrace();
        }
    }
}
