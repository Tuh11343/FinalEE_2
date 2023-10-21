        
package FinalEE.Service;

import FinalEE.Entity.ItemCollection;
import java.util.List;

public interface ItemCollectionService {
    
    ItemCollection create(ItemCollection item);
    ItemCollection update(ItemCollection item,int id);
    void delete(ItemCollection item);
    ItemCollection getItemCollection(int id);
    List<ItemCollection> getAllItemCollection();
    
}
