
package FinalEE.Repository;

import FinalEE.Entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemImageRepository extends JpaRepository<ItemImage, Integer>{
}
