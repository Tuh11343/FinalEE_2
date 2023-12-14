
package FinalEE.Repository;

import FinalEE.Entity.ItemType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer>{

    List<ItemType> findAll(Sort sort);
    List<ItemType> findAllByNameContains(String name,Sort sort);

}
