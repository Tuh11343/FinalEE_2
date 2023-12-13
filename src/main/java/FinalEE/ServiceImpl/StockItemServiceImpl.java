package FinalEE.ServiceImpl;

import FinalEE.Entity.StockItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        Optional<StockItem> stockItem=stockItemRepository.findById(id);
        return stockItem.orElse(null);
    }

    @Override
    public List<StockItem> getAllStockItem() {
        return stockItemRepository.findAll();
    }

    @Override
    public List<StockItem> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return stockItemRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StockItem> findAllByItemIDSort(Integer itemID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return stockItemRepository.findAllByItem_Id(itemID,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StockItem> findAllByItemNameSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return stockItemRepository.findAllByItem_NameLike(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
