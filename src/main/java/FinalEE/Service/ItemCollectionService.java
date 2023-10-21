        
package FinalEE.Service;

import FinalEE.Entity.ItemCollection;
import java.util.List;

public interface ItemCollectionService {
    
    boolean create(ItemCollection itemCollection);
    boolean deleteByID(int id);
    ItemCollection getItemCollection(int id);
    List<ItemCollection> getAllItemCollection();
    
}
