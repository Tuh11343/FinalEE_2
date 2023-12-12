
package FinalEE.Repository;

import FinalEE.Entity.Item;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
    @Query("SELECT i FROM Item i WHERE i.itemType.id = ?1")
    List<Item> findByItemTypeId(int itemTypeId);
    
    @Query("SELECT i FROM Item i WHERE i.itemCollection.id = ?1")
    List<Item> findByItemCollectionId(int itemCollectionId);
    
    @Query("SELECT i FROM Item i WHERE i.itemMaterial.id = ?1")
    List<Item> findByItemMaterialId(int itemMaterialId);

    @Query("SELECT i FROM Item i WHERE i.name = ?1")
    List<Item> findByItemName(String name);

    Page<Item> findAllByItemCollectionId(int itemCollectionID, Pageable pageable);

    Page<Item> findAllByItemTypeId(int itemTypeID, Pageable pageable);

    Page<Item> findAllByItemMaterialId(int itemMaterialID, Pageable pageable);

    Page<Item> findAllByNameContains(String name, Pageable pageable);

    Page<Item> findAllByPriceBetween(double min,double max,Pageable pageable);

    @Query("SELECT MIN(i.price) FROM Item i")
    Double itemMinPrice();

    @Query("SELECT MAX(i.price) FROM Item i")
    Double itemMaxPrice();

    @NonNull
    Page<Item> findAll(Pageable pageable);

}
