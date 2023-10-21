
package FinalEE.Service;

import FinalEE.Entity.ItemType;
import java.util.List;

public interface ItemTypeService {
    
    boolean create(ItemType itemType);
    boolean deleteByID(int id);
    ItemType getItemType(int id);
    List<ItemType> getAllItemType();
    
}
