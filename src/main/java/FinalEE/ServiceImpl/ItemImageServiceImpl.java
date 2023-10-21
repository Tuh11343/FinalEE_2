package FinalEE.ServiceImpl;

import FinalEE.Entity.ItemImage;
import FinalEE.Repository.ItemImageRepository;
import FinalEE.Service.ItemImageService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemImageServiceImpl implements ItemImageService{
    
    @Autowired
    private ItemImageRepository itemImageRepository;

    public ItemImageServiceImpl() {
        
    }
    
    
    
    @Override
    public ItemImage create(ItemImage itemImage) {
        return itemImageRepository.save(itemImage);
        
    }

    @Override
    public ItemImage update(ItemImage itemImage, int id) {
        
        ItemImage itemImageGet=itemImageRepository.findById(id).orElse(null);
        
        return itemImageRepository.save(itemImageGet);
    }

    @Override
    public void delete(ItemImage itemImage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ItemImage getItemImage(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ItemImage> getAllItemImage() {
        return itemImageRepository.findAll();
    }
    
    
    
}
