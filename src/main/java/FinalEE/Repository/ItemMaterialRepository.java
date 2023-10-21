
package FinalEE.Repository;

import FinalEE.Entity.ItemMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemMaterialRepository extends JpaRepository<ItemMaterial, Integer>{
}
