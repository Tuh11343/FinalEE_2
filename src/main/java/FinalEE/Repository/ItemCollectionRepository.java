
package FinalEE.Repository;

import FinalEE.Entity.ItemCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemCollectionRepository extends JpaRepository<ItemCollection, Integer>{
}
