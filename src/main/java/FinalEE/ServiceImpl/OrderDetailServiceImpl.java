package FinalEE.ServiceImpl;

import FinalEE.Entity.OrderDetail;
import FinalEE.Repository.OrderDetailRepository;
import FinalEE.Service.OrderDetailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl() {
        
    }
    
    
    
    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
        
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail, int id) {
        
        OrderDetail orderDetailGet=orderDetailRepository.findById(id).orElse(null);
        
        return orderDetailRepository.save(orderDetailGet);
    }

    @Override
    public void delete(OrderDetail orderDetail) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public OrderDetail getOrderDetail(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }
    
    
    
}
