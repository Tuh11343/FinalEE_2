package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;


public class MailTest extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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



        /*Create Customer*//*
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
        out.close();*/

        String encodedData = req.getParameter("data");

        // Kiểm tra xem tham số có tồn tại hay không
        if (encodedData != null) {
            // Giải mã dữ liệu từ tham số "data"
            Map<String, Object> keyValueData = jsonToMap(encodedData);

            // Sử dụng dữ liệu theo yêu cầu của bạn
            // Ví dụ: In ra Console
            keyValueData.forEach((key, value) -> System.out.println(key + ": " + value));


        } else {
            System.out.println("Loi");
        }

    }

    private Map<String, Object> jsonToMap(String jsonData) throws IOException {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(jsonData);
        String decodedJson = new String(decodedBytes, "UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(decodedJson);
        return objectMapper.convertValue(jsonNode, Map.class);
    }
}
