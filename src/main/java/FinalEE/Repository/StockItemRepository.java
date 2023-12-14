
package FinalEE.Repository;

import FinalEE.Entity.StockItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StockItemRepository extends JpaRepository<StockItem, Integer>{

    List<StockItem> findAll(Sort sort);
    List<StockItem> findAllByItem_Id(Integer itemID,Sort sort);
    List<StockItem> findAllByColor(String color);
    List<StockItem> findAllByColor(String color,Sort sort);

}
