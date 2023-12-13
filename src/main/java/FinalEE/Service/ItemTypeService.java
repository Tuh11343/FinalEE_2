
package FinalEE.Service;

import FinalEE.Entity.ItemType;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ItemTypeService {
    
    boolean create(ItemType itemType);
    boolean deleteByID(int id);
    ItemType getItemType(int id);
    List<ItemType> getAllItemType();

    List<ItemType> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<ItemType> findAllByNameSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    
}
