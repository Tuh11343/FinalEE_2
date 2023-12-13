
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.ItemCollection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemCollectionRepository extends JpaRepository<ItemCollection, Integer>{

    @Query("select i from ItemCollection i where i.id=?1 or i.id IS NULL")
    ItemCollection findByID(Integer id);

    List<ItemCollection> findAll(Sort sort);
    List<ItemCollection> findAllByNameLike(String name,Sort sort);
    
}
