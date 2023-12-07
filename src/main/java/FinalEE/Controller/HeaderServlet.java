package FinalEE.Controller;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.ServiceImpl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.json.JsonObject;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


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

            String requestedWith = req.getHeader("X-Requested-With");
            if (requestedWith != null && requestedWith.equals("XMLHttpRequest")) {
                String action = req.getParameter("action");

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
                    default -> {

                    }
                }

            } else {
                String action = req.getParameter("action");
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
            System.out.println(er.toString());
        }


    }

    private void signUpHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) throws JSONException {
        String name = req.getParameter("customerName");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        /*Create Customer*/Customer customer = new Customer();
        customer.setPhone_number(phoneNumber);
        customer.setName(name);
        customer.setAddress(address);
        customer.setEmail(email);

        customerServiceImpl.create(customer);

        if (customer.getId() != null) {
            Account account = new Account();
            account.setPermission(permissionServiceImpl.findByLevel(0));
            account.setCustomer(customer);
            account.setName(email);
            if (password.equals(rePassword)) {
                account.setPassword(password);
                accountServiceImpl.create(account);
                jsonResponse.put("success", 1);
            } else {
                System.out.println("Password not correct");
                jsonResponse.put("passwordIncorrect", 1);
            }
        } else {
            System.out.println("There is no customer to create Account");
            jsonResponse.put("systemError", 1);
        }
        out.print(jsonResponse);
        out.flush();
        out.close();
    }

    private void signInHandle(HttpServletRequest req, JSONObject jsonResponse, PrintWriter out) throws JSONException {
        String name = req.getParameter("signInName");
        String password = req.getParameter("signInPassword");

        Account account = accountServiceImpl.findByNameAndPassword(name, password);
        if (account != null) {
            System.out.println("Found Account");
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
    }


    private void sendEmail(String userName, String password) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("tuhtest11343@gmail.com");
            mailSender.setPassword("rceqgpucwzlyjmiv");

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String htmlMsg = "Xin chào! Để xác thực tài khoản vui lòng nhấp vào <a href='http://localhost:9595/FinalEE/MailTest?userName="
                    + userName + "&password="+ password+"'>đường link xác nhận</a>";
            helper.setText(htmlMsg, true); // true indicates the text included is HTML

            helper.setTo("dotuan2k2@gmail.com");
            helper.setSubject("Tiêu đề email");
            helper.setFrom("tuhtest11343@gmail.com");

            mailSender.send(mimeMessage);

        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    public String createActivationLink(Map<String, String> keyValueData) {
        try {
            String jsonData = mapToJson(keyValueData);
            String encodedData = Base64.getEncoder().encodeToString(jsonData.getBytes("UTF-8"));
            return "http://localhost:9595/FinalEE/activate-account?data=" + encodedData;
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
            return null;
        }
    }

    private Map<String, String> jsonToMap(String jsonData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = (ObjectNode) objectMapper.readTree(jsonData);
        Map<String, String> keyValueData = new HashMap<>();
        objectNode.fields().forEachRemaining(entry -> keyValueData.put(entry.getKey(), entry.getValue().asText()));
        return keyValueData;
    }

    private String mapToJson(Map<String, String> keyValueData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        keyValueData.forEach(objectNode::put);
        return objectMapper.writeValueAsString(objectNode);
    }

}
