package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.Entity.ItemOrder;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
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
        orderStatusServiceImpl=(OrderStatusServiceImpl) servletContext.getAttribute("orderStatusImpl");

        String encodedData = req.getParameter("data");

        if (encodedData != null) {

            MailServiceImpl mailServiceImpl = new MailServiceImpl();
            Map keyValueData = mailServiceImpl.jsonToMap(encodedData);
            switch (keyValueData.get("action").toString()) {
                case "orderConfirm" -> {
                    if (keyValueData != null) {

                        ItemOrder order= (ItemOrder) keyValueData.get("order");
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
            }
        } else {
            System.out.println("Loi");
        }

    }


}
