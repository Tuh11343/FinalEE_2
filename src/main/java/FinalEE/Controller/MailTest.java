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

import java.io.IOException;
import java.util.Base64;
import java.util.Map;


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

        String encodedData = req.getParameter("data");

        // Kiểm tra xem tham số có tồn tại hay không
        if (encodedData != null) {
            // Giải mã dữ liệu từ tham số "data"
            Map<String, Object> keyValueData = jsonToMap(encodedData);

            // Sử dụng dữ liệu theo yêu cầu của bạn
            // Ví dụ: In ra Console
            keyValueData.forEach((key, value) -> System.out.println(key + ": " + value));
            String phoneNumber= keyValueData.get("phoneNumber").toString();
            String customerName=keyValueData.get("name").toString();
            String address=keyValueData.get("address").toString();
            String email=keyValueData.get("email").toString();
            String accountName=keyValueData.get("accountName").toString();
            String accountPassword=keyValueData.get("accountPassword").toString();

            Customer customer=new Customer();
            customer.setEmail(email);
            customer.setName(customerName);
            customer.setAddress(address);
            customer.setPhone_number(phoneNumber);

            Account account=new Account();
            account.setPassword(accountPassword);
            account.setName(accountName);
            account.setPermission(permissionServiceImpl.findByLevel(0));
            account.setCustomer(customer);

            customerServiceImpl.create(customer);
            accountServiceImpl.create(account);

            resp.sendRedirect("/FinalEE/ItemServlet");

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
