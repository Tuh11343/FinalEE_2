package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemMaterial;
import FinalEE.Repository.ItemMaterialRepository;
import FinalEE.Service.ItemMaterialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemMaterialServiceImpl implements ItemMaterialService{
    
    @Autowired
    private ItemMaterialRepository itemMaterialRepository;

    public ItemMaterialServiceImpl() {
        
    }
    
    @Override
    public ItemMaterial create(ItemMaterial itemMaterial) {
        return itemMaterialRepository.save(itemMaterial);
        
    }

    @Override
    public ItemMaterial update(ItemMaterial itemMaterial, int id) {
        
        ItemMaterial itemMaterialGet=itemMaterialRepository.findById(id).orElse(null);
        
        return itemMaterialRepository.save(itemMaterialGet);
    }

    @Override
    public void delete(ItemMaterial itemMaterial) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemMaterial getItemMaterial(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemMaterial> getAllItemMaterial() {
        return itemMaterialRepository.findAll();
    }
    
    
    
}
