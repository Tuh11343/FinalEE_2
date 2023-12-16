package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemImage;
import FinalEE.Entity.ItemMaterial;
import FinalEE.Repository.ItemImageRepository;
import FinalEE.Service.ItemImageService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            ItemImage existingItemImage=itemImageRepository.findByID(itemImage.getId());

            // Lưu itemImage và kiểm tra kết quả
            itemImageRepository.save(itemImage);
            if (existingItemImage!=null) {
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
            itemImageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemImage> getAllItemImage() {
        return itemImageRepository.findAll();
    }

    @Override
    public List<ItemImage> findByItemID(int itemID) {
        return itemImageRepository.findByItemId(itemID);
    }

    @Override
    public List<ItemImage> findAllByItemID(Integer itemID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemImageRepository.findAllByItem_Id(itemID,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemImage> findAll(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemImageRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public ItemImage findByID(Integer id) {
        try{
            return itemImageRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
