
package FinalEE.Service;

import FinalEE.Entity.DiscountCard;
import FinalEE.Entity.ItemImage;
import FinalEE.Entity.ItemMaterial;
import FinalEE.ServiceImpl.ItemServiceImpl;

import java.util.List;

public interface ItemImageService {
    
    boolean create(ItemImage itemImage);
    boolean deleteByID(int id);
    List<ItemImage> getAllItemImage();
    List<ItemImage> findByItemID(int itemID);

    List<ItemImage> findAllByItemID(Integer itemID, String sort, ItemServiceImpl.SortOrder sortOrder);
    List<ItemImage> findAll(String sort, ItemServiceImpl.SortOrder sortOrder);

    ItemImage findByID(Integer id);
    
}
