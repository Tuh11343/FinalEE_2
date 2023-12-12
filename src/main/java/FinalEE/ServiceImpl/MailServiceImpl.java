package FinalEE.ServiceImpl;

import FinalEE.Service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    @Override
    public void sendMail(Map<String, Object> keyValue, String email,String message,String subject) {
        try{
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

            /*ObjectMapper objectMapper=new ObjectMapper();
            String encodedJson = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(keyValue).getBytes("UTF-8"));
            String activationLink="http://localhost:9595/FinalEE/MailTest?data="+encodedJson;

            String htmlMsg = "Xin chào! Để xác thực hóa đơn vui lòng nhấp vào <a href='" + activationLink + "'>đường link xác nhận</a>";*/
            helper.setText(message, true); // true indicates the text included is HTML

            helper.setTo(email);
            helper.setSubject(subject);
            helper.setFrom("tuhtest11343@gmail.com");

            mailSender.send(mimeMessage);

        }catch (Exception er){
            er.printStackTrace();
        }
    }

    public String mapToJSON(Map<String,Object> keyValue){
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            String encodedJson = Base64.getEncoder().encodeToString(objectMapper.writeValueAsString(keyValue).getBytes("UTF-8"));
            return "http://localhost:9595/FinalEE/MailTest?data="+encodedJson;
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public Map jsonToMap(String jsonData){
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(jsonData);
            String decodedJson = new String(decodedBytes, "UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(decodedJson, Map.class);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;

    }

}
