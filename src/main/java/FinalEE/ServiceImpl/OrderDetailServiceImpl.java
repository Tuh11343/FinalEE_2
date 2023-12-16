package FinalEE.ServiceImpl;

import FinalEE.Entity.OrderDetail;
import FinalEE.Entity.StockItem;
import FinalEE.Repository.OrderDetailRepository;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.OrderDetailService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private StockItemRepository stockItemRepository;

    public OrderDetailServiceImpl() {

    }



    @Override
    public boolean create(OrderDetail orderDetail) {
        try {
            // Kiểm tra xem orderDetail có tồn tại trong database hay không
            OrderDetail existingOrderDetail = orderDetailRepository.findByID(orderDetail.getId());

            // Lưu orderDetail và kiểm tra kết quả
            if(orderDetailRepository.save(orderDetail)!=null){
                System.out.println("Cap nhat thanh cong orderDetail:" + orderDetail.getId());
                /*Decrease Stock Item Amount*/
                if (stockItemRepository.findById(orderDetail.getStockItem().getId()).isPresent()) {
                    StockItem stockItem = orderDetail.getStockItem();
                    stockItem.setAmount(stockItem.getAmount() - 1);
                    stockItemRepository.save(stockItem);
                    System.out.println("Giam so luong thanh cong stockItem:" + stockItem.getId());
                }
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
            orderDetailRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public OrderDetail findByID(Integer id) {
        try{
            return orderDetailRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() {
        return orderDetailRepository.findAll();
    }

    @Override
    public double getOrderTotal(int orderID) {
        return orderDetailRepository.getOrderTotal(orderID);
    }

    @Override
    public List<OrderDetail> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByOrderID(Integer orderID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByOrder_Id(orderID,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByTotalLessThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByTotalIsLessThan(total,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByTotalGreaterThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByTotalIsGreaterThan(total,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
