package FinalEE.ServiceImpl;

import FinalEE.Entity.Customer;
import FinalEE.Repository.CustomerRepository;
import FinalEE.Service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl() {

    }

    @Override
    public Customer getCustomer(Integer id) {
        try{
            return customerRepository.findByID(id);
        }catch (Exception er){
            System.out.println(er.toString());
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return customerRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAllByNameLike(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return customerRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean create(Customer customer) {
        try {
            // Kiểm tra xem customer có tồn tại trong database hay không
            Customer existingCustomer=getCustomer(customer.getId());

            // Lưu customer và kiểm tra kết quả
            customerRepository.save(customer);
            if (existingCustomer!=null) {
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
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
