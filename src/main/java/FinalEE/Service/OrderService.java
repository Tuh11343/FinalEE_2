
package FinalEE.Service;

import FinalEE.Entity.Order;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    
    boolean create(Order order);
    boolean deleteByID(int id);
    List<Order> getAllOrder();

    List<Order> findByCustomerID(Integer customerID);

    List<Order> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Order> findAllByCustomerID(Integer customerID, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Order> findAllByTotalLessThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Order> findAllByTotalGreaterThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder);

    Order findByID(Integer id);
    
}
