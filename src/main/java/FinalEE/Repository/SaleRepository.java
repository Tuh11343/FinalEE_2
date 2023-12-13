
package FinalEE.Repository;

import FinalEE.Entity.Sale;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer>{

    List<Sale> findAll(Sort sort);
    List<Sale> findAllByNameLike(String name,Sort sort);

}
