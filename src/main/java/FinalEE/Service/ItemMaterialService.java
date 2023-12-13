
package FinalEE.Service;

import FinalEE.Entity.ItemMaterial;
import FinalEE.ServiceImpl.ItemServiceImpl;

import java.util.List;

public interface ItemMaterialService {
    
    boolean create(ItemMaterial itemMaterial);
    boolean deleteByID(int id);
    ItemMaterial getItemMaterial(int id);
    List<ItemMaterial> getAllItemMaterial();

    List<ItemMaterial> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<ItemMaterial> findAllByNameLikeSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
}
