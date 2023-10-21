package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemCollection;
import FinalEE.Repository.ItemCollectionRepository;
import FinalEE.Service.ItemCollectionService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemCollectionServiceImpl implements ItemCollectionService{
    
    @Autowired
    private ItemCollectionRepository itemCollectionRepository;

    public ItemCollectionServiceImpl() {
        
    }
    
    @Override
    public boolean create(ItemCollection itemCollection) {
        try {
            // Kiểm tra xem itemCollection có tồn tại trong database hay không
            Optional<ItemCollection> existingItemCollection = itemCollectionRepository.findById(itemCollection.getId());

            // Lưu itemCollection và kiểm tra kết quả
            itemCollectionRepository.save(itemCollection);
            if (existingItemCollection.isPresent()) {
                System.out.println("Cap nhat thanh cong itemCollection:" + itemCollection.getId());
            } else {
                System.out.println("Them thanh cong itemCollection:" + itemCollection.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (itemCollectionRepository.existsById(id)) {
            //itemCollectionRepository.deleteById(itemCollectionID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }

    @Override
    public ItemCollection getItemCollection(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemCollection> getAllItemCollection() {
        return itemCollectionRepository.findAll();
    }
    
    
    
}
