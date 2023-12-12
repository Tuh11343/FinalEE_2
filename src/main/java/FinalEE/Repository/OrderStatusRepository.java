
package FinalEE.Repository;

import FinalEE.Entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer>{

    @Query("select o from OrderStatus o where o.id=?1")
    OrderStatus findByID(Integer id);

}
