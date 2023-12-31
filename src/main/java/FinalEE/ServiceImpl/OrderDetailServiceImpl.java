package FinalEE.ServiceImpl;

import FinalEE.Entity.Item;
import FinalEE.Entity.Order;
import FinalEE.Entity.OrderDetail;
import FinalEE.Entity.StockItem;
import FinalEE.Repository.OrderDetailRepository;
import FinalEE.Repository.OrderRepository;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.OrderDetailService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private StockItemRepository stockItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderDetailServiceImpl() {

    }

    @Override
    public boolean create(OrderDetail orderDetail) {
        try {
            OrderDetail existingOrderDetail = orderDetailRepository.findByID(orderDetail.getId());
            orderDetailRepository.save(orderDetail);
            if(existingOrderDetail!=null){
                System.out.println("Cap nhat thanh cong orderDetail:" + existingOrderDetail.getId());
            }else{
                System.out.println("Them thanh cong orderDetail:" + orderDetail.getId());
            }

            StockItem stockItem=orderDetail.getStockItem();
            stockItem.setAmount(stockItem.getAmount() - orderDetail.getAmount());
            stockItemRepository.save(stockItem);

            Order order = orderRepository.findByID(orderDetail.getOrder().getId());
            order.setTotal(calculateOrderTotal(order));
            orderRepository.save(order);

            return true;
        } catch (Exception er) {
            er.printStackTrace();
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
        try {
            return orderDetailRepository.findByID(id);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDetail findByIDAndStockItemID(Integer orderID, Integer stockItemID) {
        try {
            return orderDetailRepository.findByOrderIdAndStockItemId(orderID, stockItemID);
        } catch (Exception er) {
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
        try {
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAll(sortBy);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByOrderID(Integer orderID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByOrder_Id(orderID, sortBy);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByTotalLessThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByTotalIsLessThan(total, sortBy);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetail> findAllByTotalGreaterThan(double total, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return orderDetailRepository.findAllByTotalIsGreaterThan(total, sortBy);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    public double calculateOrderDetailTotal(OrderDetail orderDetail) {
        Item item = orderDetail.getStockItem().getItem();
        int amount = orderDetail.getAmount();
        if (item.getSale() != null && item.getSale().getOn_sale()==1) {
            return (item.getPrice() * (1 - item.getSale().getSale_percentage() / 100)) * amount;
        } else {
            return item.getPrice() * amount;
        }
    }

    public double calculateOrderTotal(Order order) {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAllByOrder_Id(order.getId());
        if (orderDetailList != null && !orderDetailList.isEmpty()) {
            double orderTotal = 0;
            for (OrderDetail orderDetail : orderDetailList) {
                orderTotal += orderDetail.getTotal();
            }
            if (order.getDiscountCard() != null) {
                return orderTotal * (1 - (double) order.getDiscountCard().getDiscount_percentage() / 100);
            } else
                return orderTotal;
        }
        return 0;
    }


}
