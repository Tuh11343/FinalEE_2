package FinalEE.ServiceImpl;

import FinalEE.Entity.Item;
import FinalEE.Repository.ItemRepository;
import FinalEE.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    public static enum SortOrder {
        ASC, DESC
    }

    public ItemServiceImpl() {
    }


    public double getItemMinPrice(){
        try{
            return itemRepository.itemMinPrice();
        }catch (Exception er){
            er.printStackTrace();
        }
        return 0;
    }

    public double getItemMaxPrice(){
        try{
            return itemRepository.itemMaxPrice();
        }catch (Exception er){
            er.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean create(Item item) {
        try {
            // Kiểm tra xem item có tồn tại trong database hay không
            Optional<Item> existingItem = itemRepository.findById(item.getId());

            // Lưu item và kiểm tra kết quả
            itemRepository.save(item);
            if (existingItem.isPresent()) {
                System.out.println("Cap nhat thanh cong item:" + item.getId());
            } else {
                System.out.println("Them thanh cong item:" + item.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteByID(int id) {
        if (itemRepository.existsById(id)) {
            //itemRepository.deleteById(itemID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }

    @Override
    public Item getItem(int id) {
        Optional<Item> item=itemRepository.findById(id);
        return item.orElse(null);
    }

    @Override
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findByItemCollectionID(int collectionID) {
        return itemRepository.findByItemCollectionId(collectionID);
    }

    @Override
    public List<Item> findByItemMaterialID(int materialID) {
        return itemRepository.findByItemMaterialId(materialID);

    }

    @Override
    public List<Item> findByItemTypeId(int typeID) {
        return itemRepository.findByItemTypeId(typeID);
    }

    public int getTotalPages() {
        int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAll(pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemCollectionID(int itemCollectionID) {
        int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemCollectionId(itemCollectionID,pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemTypeID(int itemTypeID) {
        int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemTypeId(itemTypeID,pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemMaterialID(int itemMaterialID) {
        int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemMaterialId(itemMaterialID,pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByName(String name) {
        int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByNameContains(name,pageable);
        return items.getTotalPages();
    }

    @Override
    public List<Item> getItemsByItemCollectionIDAndPageNumber(int pageNumber,int itemCollectionID,String sort,SortOrder sortOrder){
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }
            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage=itemRepository.findAllByItemCollectionId(itemCollectionID, pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemsByItemTypeIDAndPageNumber(int pageNumber, int itemTypeID,String sort,SortOrder sortOrder) {
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }
            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage=itemRepository.findAllByItemTypeId(itemTypeID,pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemsByItemMaterialIDAndPageNumber(int pageNumber, int itemMaterialID,String sort,SortOrder sortOrder) {
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }
            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage=itemRepository.findAllByItemMaterialId(itemMaterialID,pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemsByNameAndPageNumber(int pageNumber, String name,String sort,SortOrder sortOrder) {
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }

            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage=itemRepository.findAllByNameContains(name,pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public List<Item> getItemsByPageNumber(int pageNumber,String sort,SortOrder sortOrder){
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }
            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy);
            Page<Item> itemPage=itemRepository.findAll(pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> getItemsByPriceBetweenAndPageNumber(int pageNumber,double min, double max,String sort,SortOrder sortOrder) {
        try{
            Sort sortBy;
            if(sortOrder==SortOrder.DESC){
                sortBy=Sort.by(sort).descending();
            }else{
                sortBy=Sort.by(sort).ascending();
            }
            int pageSize = 12; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize,sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage=itemRepository.findAllByPriceBetween(min,max,pageable);
            return itemPage.getContent();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

}
