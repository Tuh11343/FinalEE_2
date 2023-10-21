package FinalEE.ServiceImpl;

import FinalEE.Entity.Item;
import FinalEE.Repository.ItemRepository;
import FinalEE.Service.ItemService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ItemServiceImpl() {

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

}
