
package FinalEE.Service;

import FinalEE.Entity.StockItem;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StockItemService {
    
    boolean create(StockItem stockItem);
    boolean deleteByID(int id);
    List<StockItem> getAllStockItem();

    List<StockItem> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<StockItem> findAllByItemID(Integer itemID, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<StockItem> findAllByColor(String color, String sort, ItemServiceImpl.SortOrder sortOrder);

    StockItem findByID(Integer id);

    
}
