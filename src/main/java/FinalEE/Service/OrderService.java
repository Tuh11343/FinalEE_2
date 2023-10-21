
package FinalEE.Service;

import FinalEE.Entity.ItemOrder;
import java.util.List;

public interface OrderService {
    
    ItemOrder create(ItemOrder itemOrder);
    void delete(ItemOrder itemOrder);
    ItemOrder getOrder(int id);
    List<ItemOrder> getAllOrder();
    
}
