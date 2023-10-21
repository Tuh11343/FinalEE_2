package FinalEE.ServiceImpl;



import FinalEE.Entity.ItemOrder;
import FinalEE.Repository.OrderRepository;
import FinalEE.Service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl() {
        
    }
    
    
    @Override
    public ItemOrder create(ItemOrder order) {
        return orderRepository.save(order);
        
    }

    @Override
    public void delete(ItemOrder order) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 

    @Override
    public ItemOrder getOrder(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemOrder> getAllOrder() {
        return orderRepository.findAll();
    }
    
    
    
}
