
package FinalEE.Service;

import FinalEE.Entity.ItemImage;
import java.util.List;

public interface ItemImageService {
    
    ItemImage create(ItemImage item);
    ItemImage update(ItemImage item,int id);
    void delete(ItemImage item);
    ItemImage getItemImage(int id);
    List<ItemImage> getAllItemImage();
    
}
