
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.Sale;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer>{

    List<Sale> findAll(Sort sort);
    List<Sale> findAllByNameContains(String name,Sort sort);

    @Query("select s from Sale s where s.id=?1 or s.id IS NULL")
    Sale findByID(Integer id);

    Sale findByItem_Id(Integer itemID);
}
