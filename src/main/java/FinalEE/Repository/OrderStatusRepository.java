
package FinalEE.Repository;

import FinalEE.Entity.OrderStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

    @Query("select o from OrderStatus o where o.id=?1")
    OrderStatus findByID(Integer id);

    List<OrderStatus> findAll(Sort sort);
    List<OrderStatus> findAllByNameLike(String name,Sort sort);

}
