
package FinalEE.Service;

import FinalEE.Entity.OrderDetail;
import java.util.List;

public interface OrderDetailService {
    
    boolean create(OrderDetail orderDetail);
    boolean deleteByID(int id);
    OrderDetail getOrderDetail(int id);
    List<OrderDetail> getAllOrderDetail();
    
}
