
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.StockItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Integer>{

    List<StockItem> findAll(Sort sort);
    List<StockItem> findAllByItem_Id(Integer itemID,Sort sort);
    List<StockItem> findAllByColor(String color);
    List<StockItem> findAllByColor(String color,Sort sort);

    @Query("select si from StockItem si where si.id=?1 or si.id IS NULL")
    StockItem findByID(Integer id);

}
