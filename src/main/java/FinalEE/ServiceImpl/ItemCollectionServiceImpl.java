package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemCollection;
import FinalEE.Repository.ItemCollectionRepository;
import FinalEE.Service.ItemCollectionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemCollectionServiceImpl implements ItemCollectionService{
    
    @Autowired
    private ItemCollectionRepository itemCollectionRepository;

    public ItemCollectionServiceImpl() {
        
    }
    
    @Override
    public ItemCollection create(ItemCollection itemCollection) {
        return itemCollectionRepository.save(itemCollection);
        
    }

    @Override
    public ItemCollection update(ItemCollection itemCollection, int id) {
        
        ItemCollection itemCollectionGet=itemCollectionRepository.findById(id).orElse(null);
        
        return itemCollectionRepository.save(itemCollectionGet);
    }

    @Override
    public void delete(ItemCollection itemCollection) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
