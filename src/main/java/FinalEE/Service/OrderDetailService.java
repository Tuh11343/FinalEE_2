
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
    OrderDetail findByID(int id);
    List<OrderDetail> findAllByOrderID(Integer orderID, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<OrderDetail> findAllByTotalLessThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<OrderDetail> findAllByTotalGreaterThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder);

}
