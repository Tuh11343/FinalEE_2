        
package FinalEE.Service;

import FinalEE.Entity.ItemCollection;
import FinalEE.ServiceImpl.ItemServiceImpl;

import java.util.List;

public interface ItemCollectionService {
    
    boolean create(ItemCollection itemCollection);
    boolean deleteByID(int id);
    ItemCollection getItemCollection(int id);
    List<ItemCollection> getAllItemCollection();

    List<ItemCollection> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<ItemCollection> findAllByNameLikeSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    
}
