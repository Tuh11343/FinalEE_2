
package FinalEE.Repository;

import FinalEE.Entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<ItemOrder, Integer>{

    @Query("select o from ItemOrder o where o.id=?1")
    ItemOrder findByID(Integer id);

    List<ItemOrder> findAllByCustomer_Id(Integer customerID);

//    @Query("SELECT YEAR(io.date_purchase) as year, MONTH(io.date_purchase) as month, SUM(io.total) as revenue " +
//            "FROM ItemOrder io " +
//            "WHERE io.date_purchase >= ?1 " +
//            "GROUP BY YEAR(io.date_purchase), MONTH(io.date_purchase) " +
//            "ORDER BY YEAR DESC, month DESC")
//    List<Object[]> findRevenueForLastFiveMonths(Date fiveMonthsAgo);
}
