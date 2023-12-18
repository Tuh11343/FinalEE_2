package FinalEE.ServiceImpl;

import FinalEE.Entity.Item;
import FinalEE.Entity.StockItem;
import FinalEE.Repository.ItemRepository;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StockItemRepository stockItemRepository;

    public static enum SortOrder {
        ASC, DESC
    }

    public ItemServiceImpl() {
    }


    public double getItemMinPrice() {
        try {
            return itemRepository.itemMinPrice();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return 0;
    }

    public double getItemMaxPrice() {
        try {
            return itemRepository.itemMaxPrice();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean create(Item item) {
        try {
            // Kiểm tra xem item có tồn tại trong database hay không
            Item existingItem = itemRepository.findByID(item.getId());

            // Lưu item và kiểm tra kết quả
            itemRepository.save(item);
            if (existingItem!=null) {
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
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Item findByID(Integer id) {
        try{
            return itemRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
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

    public int getTotalPages(double min,double max) {
        int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByPriceBetween(min,max,pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemCollectionID(int itemCollectionID, double min, double max) {
        int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemCollectionIdAndPriceBetween(itemCollectionID, min, max, pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemTypeID(int itemTypeID, double min, double max) {
        int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemTypeIdAndPriceBetween(itemTypeID, min, max, pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByItemMaterialID(int itemMaterialID, double min, double max) {
        int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByItemMaterialIdAndPriceBetween(itemMaterialID, min, max, pageable);
        return items.getTotalPages();
    }

    @Override
    public int getTotalPagesByName(String name, double min, double max) {
        int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
        Pageable pageable = PageRequest.of(0, pageSize); // Bắt đầu từ trang đầu tiên
        Page<Item> items = itemRepository.findAllByNameContainsAndPriceBetween(name, min, max, pageable);
        return items.getTotalPages();
    }

    @Override
    public List<Item> findAllByItemCollectionIdAndPriceBetween(int pageNumber, int itemCollectionID, double min, double max, String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage = itemRepository.findAllByItemCollectionIdAndPriceBetween(itemCollectionID, min, max, pageable);
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByItemTypeIdAndPriceBetween(int pageNumber, int itemTypeID, double min,double max,String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage = itemRepository.findAllByItemTypeIdAndPriceBetween(itemTypeID,min,max,pageable);
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByItemMaterialIdAndPriceBetween(int pageNumber, int itemMaterialID,double min,double max ,String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage = itemRepository.findAllByItemMaterialIdAndPriceBetween(itemMaterialID, min,max,pageable);
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByNameContainsAndPriceBetween(int pageNumber, String name,double min,double max, String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }

            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy); // Trừ 1 vì số trang bắt đầu từ 0
            Page<Item> itemPage = itemRepository.findAllByNameContainsAndPriceBetween(name,min,max,pageable);
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByPriceBetween(int pageNumber,double min,double max, String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy);
            Page<Item> itemPage = itemRepository.findAllByPriceBetween(min,max,pageable);
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findItemListByColor(String color) {
        List<StockItem> stockItems = stockItemRepository.findAllByColor(color);
        return stockItems.stream().map(StockItem::getItem).collect(Collectors.toList());
    }

    @Override
    public List<Item> findItemListByColor(String color, String sort, SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            List<StockItem> stockItems = stockItemRepository.findAllByColor(color,sortBy);
            return stockItems.stream().map(StockItem::getItem).collect(Collectors.toList());
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByPriceLessThan(double price, String sort, SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemRepository.findAllByPriceLessThan(price,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findAllByPriceGreaterThan(double price, String sort, SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemRepository.findAllByPriceGreaterThan(price,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Item> findByColor(int pageNumber, String color, String sort, SortOrder sortOrder) {
        try {
            Sort sortBy;
            if (sortOrder == SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            int pageSize = 8; // Số lượng sản phẩm trên mỗi trang
            Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sortBy);
            List<Item> itemList = findItemListByColor(color);

            int start = (int) pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), itemList.size());

            Page<Item> itemPage = new PageImpl<>(itemList.subList(start, end), pageable, itemList.size());
            return itemPage.getContent();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

}
