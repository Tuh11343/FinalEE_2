
package FinalEE.Service;

import FinalEE.Entity.ItemOrder;
import java.util.List;

public interface OrderService {
    
    boolean create(ItemOrder order);
    boolean deleteByID(int id);
    ItemOrder getOrder(int id);
    List<ItemOrder> getAllOrder();
    
}
