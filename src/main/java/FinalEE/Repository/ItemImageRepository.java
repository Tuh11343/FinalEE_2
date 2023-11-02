
package FinalEE.Repository;

import FinalEE.Entity.Item;
import FinalEE.Entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Integer>{

    @Query("SELECT i FROM ItemImage i WHERE i.item.id = ?1")
    List<ItemImage> findByItemId(int itemID);

}
