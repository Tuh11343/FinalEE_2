
package FinalEE.Repository;

import FinalEE.Entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
    @Query("SELECT i FROM Item i WHERE i.itemType.id = ?1")
    List<Item> findByItemTypeId(int itemTypeId);
    
    @Query("SELECT i FROM Item i WHERE i.itemCollection.id = ?1")
    List<Item> findByItemCollectionId(int itemCollectionId);
    
    @Query("SELECT i FROM Item i WHERE i.itemMaterial.id = ?1")
    List<Item> findByItemMaterialId(int itemMaterialId);
}
