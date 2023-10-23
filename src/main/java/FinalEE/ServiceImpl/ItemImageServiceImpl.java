package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemImage;
import FinalEE.Repository.ItemImageRepository;
import FinalEE.Service.ItemImageService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemImageServiceImpl implements ItemImageService{
    
    @Autowired
    private ItemImageRepository itemImageRepository;

    public ItemImageServiceImpl() {
        
    }
    
    
    
    @Override
    public boolean create(ItemImage itemImage) {
        try {
            // Kiểm tra xem itemImage có tồn tại trong database hay không
            Optional<ItemImage> existingItemImage = itemImageRepository.findById(itemImage.getId());

            // Lưu itemImage và kiểm tra kết quả
            itemImageRepository.save(itemImage);
            if (existingItemImage.isPresent()) {
                System.out.println("Cap nhat thanh cong itemImage:" + itemImage.getId());
            } else {
                System.out.println("Them thanh cong itemImage:" + itemImage.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (itemImageRepository.existsById(id)) {
            //itemImageRepository.deleteById(itemImageID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }

    @Override
    public ItemImage getItemImage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemImage> getAllItemImage() {
        return itemImageRepository.findAll();
    }

    @Override
    public List<ItemImage> findByItemID(int itemID) {
        return itemImageRepository.findByItemId(itemID);
    }


}
