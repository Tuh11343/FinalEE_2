package FinalEE.Service;

import FinalEE.Entity.Customer;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {

    boolean create(Customer customer);
    boolean deleteByID(int id);
    Customer getCustomer(Integer id);
    List<Customer> getAllCustomer();

    List<Customer> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Customer> findAllByNameLike(String name, String sort, ItemServiceImpl.SortOrder sortOrder);


}
