package FinalEE.ServiceImpl;

import FinalEE.Entity.StockItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.StockItemService;
import java.util.Optional;


@Service
public class StockItemServiceImpl implements StockItemService{
    
    @Autowired
    private StockItemRepository stockItemRepository;

    public StockItemServiceImpl() {
        
    }
    
    @Override
    public boolean create(StockItem stockItem) {
        try {
            // Kiểm tra xem stockItem có tồn tại trong database hay không
            Optional<StockItem> existingStockItem = stockItemRepository.findById(stockItem.getId());

            // Lưu stockItem và kiểm tra kết quả
            stockItemRepository.save(stockItem);
            if (existingStockItem.isPresent()) {
                System.out.println("Cap nhat thanh cong stockItem:" + stockItem.getId());
            } else {
                System.out.println("Them thanh cong stockItem:" + stockItem.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (stockItemRepository.existsById(id)) {
            //stockItemRepository.deleteById(stockItemID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }
    

    @Override
    public StockItem getStockItem(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<StockItem> getAllStockItem() {
        return stockItemRepository.findAll();
    }
    
    
    
}
