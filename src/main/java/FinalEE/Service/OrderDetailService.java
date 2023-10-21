
package FinalEE.Service;

import FinalEE.Entity.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    
    OrderDetail create(OrderDetail item);
    OrderDetail update(OrderDetail item,int id);
    void delete(OrderDetail item);
    OrderDetail getOrderDetail(int id);
    List<OrderDetail> getAllOrderDetail();
    
}
