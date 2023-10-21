
package FinalEE.Service;

import FinalEE.Entity.StockItem;
import java.util.List;

public interface StockItemService {
    
    boolean create(StockItem stockItem);
    boolean deleteByID(int id);
    StockItem getStockItem(int id);
    List<StockItem> getAllStockItem();
    
}
