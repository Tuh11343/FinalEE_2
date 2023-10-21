
package FinalEE.Service;

import FinalEE.Entity.ItemType;
import java.util.List;

public interface ItemTypeService {
    
    ItemType create(ItemType item);
    ItemType update(ItemType item,int id);
    void delete(ItemType item);
    ItemType getItemType(int id);
    List<ItemType> getAllItemType();
    
}
