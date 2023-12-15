package FinalEE.ServiceImpl;



import FinalEE.Entity.Order;
import FinalEE.Repository.OrderRepository;
import FinalEE.Service.OrderService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl() {
        
    }
    
    
    @Override
    public boolean create(Order order) {
        try {
            Order existingOrder;
            Integer orderID = order.getId() != null ? order.getId() : null;
            existingOrder = orderRepository.findByID(orderID);

            // Lưu order và kiểm tra kết quả
            orderRepository.save(order);
            if (existingOrder!=null) {
                System.out.println("Cap nhat thanh cong order:" + order.getId());
            } else {
                System.out.println("Them thanh cong order:" + order.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
 

    @Override
    public Order getOrder(int id) {
        try{
            return orderRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findByCustomerID(Integer customerID) {
        return orderRepository.findAllByCustomer_Id(customerID);
    }

    @Override
    public List<Order> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAllByCustomerID(Integer customerID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderRepository.findAllByCustomer_Id(customerID,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAllByTotalLessThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderRepository.findAllByTotalIsLessThan(total,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAllByTotalGreaterThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderRepository.findAllByTotalIsGreaterThan(total,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
