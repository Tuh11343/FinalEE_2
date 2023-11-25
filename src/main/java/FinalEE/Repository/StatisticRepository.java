
package FinalEE.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public class StatisticRepository{

    @Autowired
    EntityManager entityManager;

    public List<Object[]> productsSoldByMonth(int month) {
        try{
            String sql = "SELECT od.item_id, SUM(od.amount) AS total_quantity\n" +
                    "    FROM itemorderdetail od\n" +
                    "    JOIN itemorder o ON od.order_id = o.id\n" +
                    "    WHERE MONTH(o.date_purchase) = ?1\n" +
                    "    GROUP BY od.item_id\n" +
                    "    ORDER BY total_quantity DESC\n" +
                    "    LIMIT 5";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,month);
            return query.getResultList();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> productsSoldByYear(int year) {
        try{
            String sql = "SELECT od.item_id, SUM(od.amount) AS total_quantity\n" +
                    "    FROM itemorderdetail od\n" +
                    "    JOIN itemorder o ON od.order_id = o.id\n" +
                    "    WHERE YEAR(o.date_purchase) = ?1\n" +
                    "    GROUP BY od.item_id\n" +
                    "    ORDER BY total_quantity DESC\n" +
                    "    LIMIT 5";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,year);
            return query.getResultList();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> totalProductsSoldByMonth(int month) {
        try{
            String sql = "SELECT od.item_id, SUM(od.amount) AS total_quantity\n" +
                    "    FROM itemorderdetail od\n" +
                    "    JOIN itemorder o ON od.order_id = o.id\n" +
                    "    WHERE MONTH(o.date_purchase) = ?1\n" +
                    "    GROUP BY od.item_id\n" +
                    "    ORDER BY total_quantity DESC";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,month);
            return query.getResultList();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> totalProductsSoldByYear(int year) {
        try{
            String sql = "SELECT od.item_id, SUM(od.amount) AS total_quantity\n" +
                    "    FROM itemorderdetail od\n" +
                    "    JOIN itemorder o ON od.order_id = o.id\n" +
                    "    WHERE YEAR(o.date_purchase) = ?1\n" +
                    "    GROUP BY od.item_id\n" +
                    "    ORDER BY total_quantity DESC";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,year);
            return query.getResultList();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    public List<Object[]> getRevenueByMonth(Date month) {
        try{
            String sql = "SELECT YEAR(io.date_purchase) as year, MONTH(io.date_purchase) as month, SUM(io.total) as revenue\n" +
                    "    FROM ItemOrder as io\n" +
                    "    WHERE io.date_purchase >= ?1\n" +
                    "    GROUP BY YEAR(io.date_purchase), MONTH(io.date_purchase)\n" +
                    "    ORDER BY YEAR DESC, month DESC";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,month);
            return query.getResultList();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }





}
