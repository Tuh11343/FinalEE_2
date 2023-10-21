package FinalEE.ServiceImpl;

import FinalEE.Entity.OrderDetail;
import FinalEE.Repository.OrderDetailRepository;
import FinalEE.Service.OrderDetailService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{
    
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl() {
        
    }
    
    
    
    @Override
    public boolean create(OrderDetail orderDetail) {
        try {
            // Kiểm tra xem orderDetail có tồn tại trong database hay không
            Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findById(orderDetail.getId());

            // Lưu orderDetail và kiểm tra kết quả
            orderDetailRepository.save(orderDetail);
            if (existingOrderDetail.isPresent()) {
                System.out.println("Cap nhat thanh cong orderDetail:" + orderDetail.getId());
            } else {
                System.out.println("Them thanh cong orderDetail:" + orderDetail.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (orderDetailRepository.existsById(id)) {
            //orderDetailRepository.deleteById(orderDetailID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
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
