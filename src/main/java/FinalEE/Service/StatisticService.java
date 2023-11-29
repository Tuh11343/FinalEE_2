
package FinalEE.Service;

import FinalEE.Entity.Account;

import java.util.Date;
import java.util.List;

public interface StatisticService{

    List<Object[]> productsSoldByMonth(int month,int year);

    List<Object[]> productsSoldByYear(int year);

    List<Object[]> totalProductsSoldByMonth(int month,int year);

    List<Object[]> totalProductsSoldByYear(int year);

    List<Object[]> getRevenueByMonth(int month,int year);

    List<Object[]> getRecentFiveMonthRevenue();


    
}
