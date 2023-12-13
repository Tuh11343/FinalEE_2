
package FinalEE.Repository;

import FinalEE.Entity.ItemOrder;
import FinalEE.Entity.OrderDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    @Query("SELECT COALESCE(SUM(od.total), 0.0) FROM OrderDetail od WHERE od.order.id = ?1")
    double getOrderTotal(int orderID);

    List<OrderDetail> findAll(Sort sort);
    List<OrderDetail> findAllByOrder(ItemOrder itemOrder, Sort sort);

}
