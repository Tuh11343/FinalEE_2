
package FinalEE.Service;

import FinalEE.Entity.ItemOrder;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    
    boolean create(ItemOrder order);
    boolean deleteByID(int id);
    ItemOrder getOrder(int id);
    List<ItemOrder> getAllOrder();

    List<ItemOrder> findByCustomerID(Integer customerID);

    List<ItemOrder> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<ItemOrder> findAllByCustomerName(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    
}
