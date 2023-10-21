
package FinalEE.Service;

import FinalEE.Entity.ItemMaterial;
import java.util.List;

public interface ItemMaterialService {
    
    ItemMaterial create(ItemMaterial item);
    ItemMaterial update(ItemMaterial item,int id);
    void delete(ItemMaterial item);
    ItemMaterial getItemMaterial(int id);
    List<ItemMaterial> getAllItemMaterial();
    
}
