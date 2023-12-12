
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
    public double getItemMinPrice();
    public double getItemMaxPrice();

    int getTotalPagesByItemCollectionID(int itemCollectionID,double min,double max);
    int getTotalPagesByItemTypeID(int itemTypeID,double min,double max);
    int getTotalPagesByItemMaterialID(int materialID,double min,double max);
    int getTotalPagesByName(String name,double min,double max);
    int getTotalPages();

    public List<Item> findAllByItemCollectionIdAndPriceBetween(int pageNumber, int itemCollectionID,double min,double max, String sort, ItemServiceImpl.SortOrder sortOrder);
    public List<Item> findAllByItemTypeIdAndPriceBetween(int pageNumber,int itemTypeID,double min,double max,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> findAllByItemMaterialIdAndPriceBetween(int pageNumber,int itemMaterialID,double min,double max,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> findAllByNameContainsAndPriceBetween(int pageNumber,String name,double min,double max,String sort,ItemServiceImpl.SortOrder sortOrder);
    public List<Item> findAllByPriceBetween(int pageNumber,double min,double max,String sort,ItemServiceImpl.SortOrder sortOrder);


    
}
