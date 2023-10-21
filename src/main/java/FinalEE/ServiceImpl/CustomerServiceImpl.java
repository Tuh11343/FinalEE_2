package FinalEE.ServiceImpl;

import FinalEE.Entity.Customer;
import FinalEE.Repository.CustomerRepository;
import FinalEE.Service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {

    }

    @Override
    public Customer getCustomer(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean create(Customer customer) {
        try {
            // Kiểm tra xem customer có tồn tại trong database hay không
            Optional<Customer> existingCustomer = customerRepository.findById(customer.getId());

            // Lưu customer và kiểm tra kết quả
            customerRepository.save(customer);
            if (existingCustomer.isPresent()) {
                System.out.println("Cap nhat thanh cong customer:" + customer.getId());
            } else {
                System.out.println("Them thanh cong customer:" + customer.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (customerRepository.existsById(id)) {
            //customerRepository.deleteById(customerID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }

}
