package FinalEE.Service;

import java.util.Map;

public interface MailService {

    public void sendMail(Map<String,Object> keyValue,String email,String message,String subject);

    public String mapToJSON(Map<String,Object> keyValue);

    public Map<String,Object> jsonToMap(String jsonData);

}
