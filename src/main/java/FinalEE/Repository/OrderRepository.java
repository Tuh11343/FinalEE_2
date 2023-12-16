
package FinalEE.Repository;

import FinalEE.Entity.Account;
import FinalEE.Entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

    List<Order> findAllByCustomer_Id(Integer customerID);

//    @Query("SELECT YEAR(io.date_purchase) as year, MONTH(io.date_purchase) as month, SUM(io.total) as revenue " +
//            "FROM ItemOrder io " +
//            "WHERE io.date_purchase >= ?1 " +
//            "GROUP BY YEAR(io.date_purchase), MONTH(io.date_purchase) " +
//            "ORDER BY YEAR DESC, month DESC")
//    List<Object[]> findRevenueForLastFiveMonths(Date fiveMonthsAgo);

    List<Order> findAll(Sort sort);
    List<Order> findAllByCustomer_Id(Integer customerID,Sort sort);
    List<Order> findAllByTotalIsGreaterThan(double total,Sort sort);
    List<Order> findAllByTotalIsLessThan(double total,Sort sort);

    @Query("select o from Order o where o.id=?1 or o.id IS NULL")
    Order findByID(Integer id);

}
