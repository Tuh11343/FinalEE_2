
package FinalEE.Service;

import FinalEE.Entity.Item;
import java.util.List;

public interface ItemService {
    
    boolean create(Item item);
    boolean deleteByID(int id);
    Item getItem(int id);
    List<Item> getAllItem();
    List<Item> findByItemCollectionID(int itemCollectionID);
    List<Item> findByItemMaterialID(int itemMaterialID);
    List<Item> findByItemTypeId(int typeID);


    int getTotalPagesByItemCollectionID(int itemCollectionID);
    int getTotalPagesByItemTypeID(int itemTypeID);
    int getTotalPagesByItemMaterialID(int materialID);
    int getTotalPagesByName(String name);
    int getTotalPages();

    public List<Item> getItemsByItemCollectionIDAndPageNumber(int pageNumber,int itemCollectionID);
    public List<Item> getItemsByItemTypeIDAndPageNumber(int pageNumber,int itemTypeID);
    public List<Item> getItemsByItemMaterialIDAndPageNumber(int pageNumber,int itemMaterialID);
    public List<Item> getItemsByNameAndPageNumber(int pageNumber,String name);
    public List<Item> getItemsByPageNumber(int pageNumber);

    
}
