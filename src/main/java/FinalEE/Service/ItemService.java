
package FinalEE.Service;

import FinalEE.Entity.Item;
import java.util.List;

public interface ItemService {
    
    boolean create(Item item);
    boolean deleteByID(int id);
    Item getItem(int id);
    List<Item> getAllItem();
    List<Item> findByItemCollectionID(int collectionID);
    List<Item> findByItemMaterialID(int materialID);
    List<Item> findByItemTypeId(int typeID);
    
}
