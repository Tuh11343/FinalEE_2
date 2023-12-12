
package FinalEE.Service;


import FinalEE.Entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {
    
    boolean create(OrderStatus orderStatus);
    boolean deleteByID(int orderStatusID);
    List<OrderStatus> getAllOrderStatus();
    OrderStatus findByID(Integer orderStatusID);

    OrderStatus defaultOrder();
    OrderStatus confirmOrder();

}
