package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemType;
import FinalEE.Repository.ItemTypeRepository;
import FinalEE.Service.ItemTypeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemTypeServiceImpl implements ItemTypeService{
    
    @Autowired
    private ItemTypeRepository itemTypeRepository;

    public ItemTypeServiceImpl() {
        
    }
    
    
    
    @Override
    public ItemType create(ItemType itemType) {
        return itemTypeRepository.save(itemType);
        
    }

    @Override
    public ItemType update(ItemType itemType, int id) {
        
        ItemType itemTypeGet=itemTypeRepository.findById(id).orElse(null);
        
        return itemTypeRepository.save(itemTypeGet);
    }

    @Override
    public void delete(ItemType itemType) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemType getItemType(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemType> getAllItemType() {
        return itemTypeRepository.findAll();
    }
    
    
    
}
