
package FinalEE.Service;

import FinalEE.Entity.ItemMaterial;
import java.util.List;

public interface ItemMaterialService {
    
    boolean create(ItemMaterial itemMaterial);
    boolean deleteByID(int id);
    ItemMaterial getItemMaterial(int id);
    List<ItemMaterial> getAllItemMaterial();
    
}
