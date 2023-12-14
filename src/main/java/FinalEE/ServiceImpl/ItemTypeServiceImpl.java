package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemType;
import FinalEE.Repository.ItemTypeRepository;
import FinalEE.Service.ItemTypeService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ItemTypeServiceImpl implements ItemTypeService{
    
    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public ItemTypeServiceImpl() {
        
    }
    
    @Override
    public boolean create(ItemType itemType) {
        try {
            // Kiểm tra xem itemType có tồn tại trong database hay không
            Optional<ItemType> existingItemType = itemTypeRepository.findById(itemType.getId());

            // Lưu itemType và kiểm tra kết quả
            itemTypeRepository.save(itemType);
            if (existingItemType.isPresent()) {
                System.out.println("Cap nhat thanh cong itemType:" + itemType.getId());
            } else {
                System.out.println("Them thanh cong itemType:" + itemType.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (itemTypeRepository.existsById(id)) {
            //itemTypeRepository.deleteById(itemTypeID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }

    @Override
    public ItemType getItemType(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemType> getAllItemType() {
        return itemTypeRepository.findAll();
    }

    @Override
    public List<ItemType> findAll(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemTypeRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemType> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemTypeRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
