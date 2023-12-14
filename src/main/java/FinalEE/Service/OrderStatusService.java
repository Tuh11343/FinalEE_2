
package FinalEE.Service;


import FinalEE.Entity.OrderStatus;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderStatusService {
    
    boolean create(OrderStatus orderStatus);
    boolean deleteByID(int orderStatusID);
    List<OrderStatus> getAllOrderStatus();
    OrderStatus findByID(Integer orderStatusID);

    OrderStatus defaultOrder();
    OrderStatus confirmOrder();

    List<OrderStatus> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<OrderStatus> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    OrderStatus findById(int orderStatusID);

}
