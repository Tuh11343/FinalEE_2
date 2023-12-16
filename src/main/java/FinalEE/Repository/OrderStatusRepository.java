
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

    List<OrderStatus> findAll(Sort sort);
    List<OrderStatus> findAllByNameContains(String name,Sort sort);
    OrderStatus findById(int orderStatusID);

    @Query("select od from OrderStatus od where od.id=?1 or od.id IS NULL")
    OrderStatus findByID(Integer id);

}
