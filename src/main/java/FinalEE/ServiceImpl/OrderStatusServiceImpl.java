package FinalEE.ServiceImpl;

import FinalEE.Entity.OrderStatus;
import FinalEE.Repository.OrderStatusRepository;
import FinalEE.Service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImpl() {

    }

    @Override
    public boolean create(OrderStatus orderStatus) {
        try {
            // Kiểm tra xem orderStatus có tồn tại trong database hay không
            OrderStatus existingOrderStatus=findByID(orderStatus.getId());

            // Lưu orderStatus và kiểm tra kết quả
            orderStatusRepository.save(orderStatus);
            if (existingOrderStatus!=null) {
                System.out.println("Cap nhat thanh cong orderStatus:" + orderStatus.getId());
            } else {
                System.out.println("Them thanh cong orderStatus:" + orderStatus.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteByID(int orderStatusID) {
        if (orderStatusRepository.existsById(orderStatusID)) {
            orderStatusRepository.deleteById(orderStatusID);
            System.out.println("Ban da xoa:" + orderStatusID);
            return true;
        }
        return false;

    }

    @Override
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus findByID(Integer orderStatusID) {
        try{
            return orderStatusRepository.findByID(orderStatusID);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderStatus defaultOrder() {
        try{
            return orderStatusRepository.findByID(1);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderStatus confirmOrder() {
        try{
            return orderStatusRepository.findByID(2);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderStatus> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderStatusRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderStatus> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderStatusRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
