package FinalEE.ServiceImpl;

import FinalEE.Entity.StockItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.StockItemService;


@Service
public class StockItemServiceImpl implements StockItemService{
    
    @Autowired
    private StockItemRepository stockItemRepository;

    public StockItemServiceImpl() {
        
    }
    
    
    
    @Override
    public StockItem create(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
        
    }

    @Override
    public StockItem update(StockItem stockItem, int id) {
        
        StockItem stockItemGet=stockItemRepository.findById(id).orElse(null);
        
        return stockItemRepository.save(stockItemGet);
    }

    @Override
    public void delete(StockItem stockItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
