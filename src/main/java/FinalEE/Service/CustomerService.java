package FinalEE.Service;

import FinalEE.Entity.Customer;
import java.util.List;

public interface CustomerService {

    boolean create(Customer customer);
    boolean deleteByID(int id);
    Customer getCustomer(Integer id);
    List<Customer> getAllCustomer();

}
