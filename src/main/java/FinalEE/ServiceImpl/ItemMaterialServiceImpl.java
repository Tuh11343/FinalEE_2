package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemMaterial;
import FinalEE.Repository.ItemMaterialRepository;
import FinalEE.Service.ItemMaterialService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ItemMaterialServiceImpl implements ItemMaterialService{
    
    @Autowired
    private ItemMaterialRepository itemMaterialRepository;

    public ItemMaterialServiceImpl() {
        
    }
    
    @Override
    public boolean create(ItemMaterial itemMaterial) {
        try {
            // Kiểm tra xem itemMaterial có tồn tại trong database hay không
            ItemMaterial existingItemMaterial=itemMaterialRepository.findByID(itemMaterial.getId());

            // Lưu itemMaterial và kiểm tra kết quả
            itemMaterialRepository.save(itemMaterial);
            if (existingItemMaterial!=null) {
                System.out.println("Cap nhat thanh cong itemMaterial:" + itemMaterial.getId());
            } else {
                System.out.println("Them thanh cong itemMaterial:" + itemMaterial.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (itemMaterialRepository.existsById(id)) {
            itemMaterialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ItemMaterial findByID(Integer id) {
        try{
            return itemMaterialRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemMaterial> getAllItemMaterial() {
        return itemMaterialRepository.findAll();
    }

    @Override
    public List<ItemMaterial> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemMaterialRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemMaterial> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return itemMaterialRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }


}
