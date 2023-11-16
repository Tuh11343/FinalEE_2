package FinalEE.ServiceImpl;



import FinalEE.Entity.Cart;
import FinalEE.Entity.ItemOrder;
import FinalEE.Repository.OrderRepository;
import FinalEE.Service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl() {
        
    }
    
    
    @Override
    public boolean create(ItemOrder order) {
        try {
            ItemOrder existingOrder;
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
            //orderRepository.deleteById(orderID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }
 

    @Override
    public ItemOrder getOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemOrder> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<ItemOrder> findByCustomerID(Integer customerID) {
        return orderRepository.findAllByCustomer_Id(customerID);
    }


}
