
package FinalEE.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class StatisticRepository {

    @Autowired
    EntityManager entityManager;

    public List<Object[]> getTop10BestSellingProducts() {
        try {
            String sql = "SELECT i.id, i.name, SUM(od.amount) as total " +
                    "FROM item i " +
                    "JOIN stockitem si ON i.id = si.item_id " +
                    "JOIN itemorderdetail od ON si.id = od.stock_item_id " +
                    "GROUP BY i.id " +
                    "ORDER BY total DESC " +
                    "LIMIT 10";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getTop5CustomersByRevenue() {
        try {
            String sql = "SELECT c.id, c.name, SUM(o.total) as total_revenue " +
                    "FROM Customer c " +
                    "JOIN ItemOrder o ON c.id = o.customer_id " +
                    "GROUP BY c.id " +
                    "ORDER BY total_revenue DESC " +
                    "LIMIT 5";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getTop5CustomersByAmount() {
        try {
            String sql = "SELECT c.id, c.name, SUM(od.amount) as total " +
                    "FROM Customer c " +
                    "JOIN ItemOrder o ON c.id = o.customer_id " +
                    "JOIN OrderDetail od ON o.id = od.order_id " +
                    "GROUP BY c.id " +
                    "ORDER BY total DESC " +
                    "LIMIT 5";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getRecentFiveMonthRevenue() {
        try {
            String sql = "SELECT YEAR(o.date_purchase) as year, MONTH(o.date_purchase) as month, SUM(o.total) as revenue " +
                    "FROM ItemOrder o " +
                    "GROUP BY YEAR(o.date_purchase), MONTH(o.date_purchase) " +
                    "ORDER BY year DESC, month DESC " +
                    "LIMIT 5";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getRevenueByDayOfWeek() {
        try {
            String sql = "SELECT DAYOFWEEK(o.date_purchase) as day_of_week, SUM(o.total) as revenue " +
                    "FROM ItemOrder o " +
                    "GROUP BY DAYOFWEEK(o.date_purchase) " +
                    "ORDER BY revenue DESC";
            Query query = entityManager.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }


}
