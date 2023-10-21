
package FinalEE.Service;

import FinalEE.Entity.ItemImage;
import java.util.List;

public interface ItemImageService {
    
    boolean create(ItemImage itemImage);
    boolean deleteByID(int id);
    ItemImage getItemImage(int id);
    List<ItemImage> getAllItemImage();
    
}
