
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.ItemType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer>{

    List<ItemType> findAll(Sort sort);
    List<ItemType> findAllByNameContains(String name,Sort sort);

    @Query("select it from ItemType it where it.id=?1 or it.id IS NULL")
    ItemType findByID(Integer id);

}
