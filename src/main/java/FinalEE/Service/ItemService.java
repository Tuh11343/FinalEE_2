
package FinalEE.Service;

import FinalEE.Entity.Item;
import FinalEE.ServiceImpl.ItemServiceImpl;

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

    public List<Item> getItemsByItemCollectionIDAndPageNumber(int pageNumber, int itemCollectionID, String sort, ItemServiceImpl.SortOrder sortOrder);
    public List<Item> getItemsByItemTypeIDAndPageNumber(int pageNumber,int itemTypeID,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> getItemsByItemMaterialIDAndPageNumber(int pageNumber,int itemMaterialID,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> getItemsByNameAndPageNumber(int pageNumber,String name,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> getItemsByPageNumber(int pageNumber,String sort,ItemServiceImpl.SortOrder sortOrder);

    public List<Item> getItemsByPriceBetween(int pageNumber, double min,double max,String sort,ItemServiceImpl.SortOrder sortOrder);

    
}
