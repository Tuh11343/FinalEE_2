    
package FinalEE.Service;

import FinalEE.Entity.DiscountCard;
import java.util.List;

public interface DiscountCardService {
    
    boolean create(DiscountCard discountCard);
    boolean deleteByID(int id);
    DiscountCard getDiscountCard(int id);
    List<DiscountCard> getAllDiscountCard();
    List<DiscountCard> findByCustomerID(Integer customerID);
    
}
