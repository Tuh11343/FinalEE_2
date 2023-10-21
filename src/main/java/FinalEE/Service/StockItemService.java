
package FinalEE.Service;

import FinalEE.Entity.StockItem;
import java.util.List;

public interface StockItemService {
    
    StockItem create(StockItem stockItem);
    StockItem update(StockItem stockItem,int id);
    void delete(StockItem stockItem);
    StockItem getStockItem(int id);
    List<StockItem> getAllStockItem();
    
}
