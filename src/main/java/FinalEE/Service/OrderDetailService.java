
package FinalEE.Service;

import FinalEE.Entity.OrderDetail;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderDetailService {

    boolean create(OrderDetail orderDetail);
    boolean deleteByID(int id);
    OrderDetail getOrderDetail(int id);
    List<OrderDetail> getAllOrderDetail();

    double getOrderTotal(int orderID);

    List<OrderDetail> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);

}
