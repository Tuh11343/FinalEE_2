    
package FinalEE.Service;

import FinalEE.Entity.DiscountCard;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DiscountCardService {
    
    boolean create(DiscountCard discountCard);
    boolean deleteByID(int id);
    DiscountCard getDiscountCard(Integer id);
    List<DiscountCard> getAllDiscountCard();
    List<DiscountCard> findByCustomerID(Integer customerID);

    List<DiscountCard> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);

    List<DiscountCard> findAllByNameLikeSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    
}
