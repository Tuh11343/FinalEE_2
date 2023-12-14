
package FinalEE.Repository;

import FinalEE.Entity.ItemMaterial;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Integer>{

    List<ItemMaterial> findAll(Sort sort);
    List<ItemMaterial> findAllByNameContains(String name,Sort sort);

}
