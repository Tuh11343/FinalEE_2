package FinalEE.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test {

    public static void main(String[] args) {
        sendEmail("username","password");
    }

    private static void sendEmail(String userName, String password) {
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

            Map<String,Object> keyValue=new HashMap<>();
            keyValue.put("phoneNumber",113);
            keyValue.put("name","User Name");
            keyValue.put("address","Dia Chi");
            keyValue.put("email","useremail@gmail.com");
            keyValue.put("accountName","accountName");
            keyValue.put("accountPassword","account Password");

            String activationLink=createActivationLink(keyValue);

            String htmlMsg = "Xin chào! Để xác thực tài khoản vui lòng nhấp vào <a href='"+ activationLink+"'>đường link xác nhận</a>";
            helper.setText(htmlMsg, true); // true indicates the text included is HTML

            helper.setTo("dotuan2k2@gmail.com");
            helper.setSubject("Tiêu đề email");
            helper.setFrom("tuhtest11343@gmail.com");

            mailSender.send(mimeMessage);

        } catch (Exception er) {
            er.printStackTrace();
        }

    }

    public static String createActivationLink(Map<String, Object> keyValueData) {
        try {
            String jsonData = mapToJson(keyValueData);
            String encodedData = Base64.getEncoder().encodeToString(jsonData.getBytes("UTF-8"));
            return "http://localhost:9595/FinalEE/MailTest?data=" + encodedData;
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
            return null;
        }
    }

    private static String mapToJson(Map<String, Object> keyValueData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        keyValueData.forEach((key, value) -> {
            if (value instanceof String) {
                objectNode.put(key, (String) value);
            } else {
                System.out.println("Loi roi");
            }
        });
        return objectMapper.writeValueAsString(objectNode);
    }

}
