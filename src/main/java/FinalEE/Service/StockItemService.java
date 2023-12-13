
package FinalEE.Service;

import FinalEE.Entity.StockItem;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StockItemService {
    
    boolean create(StockItem stockItem);
    boolean deleteByID(int id);
    StockItem getStockItem(int id);
    List<StockItem> getAllStockItem();

    List<StockItem> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<StockItem> findAllByItemIDSort(Integer itemID, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<StockItem> findAllByItemNameSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);

    
}
