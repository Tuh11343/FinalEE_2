
package FinalEE.Service;

import FinalEE.Entity.Account;

import java.util.Date;
import java.util.List;

public interface StatisticService{

    public List<Object[]> getTop10BestSellingProducts();
    public List<Object[]> getTop5CustomersByRevenue();
    public List<Object[]> getTop5CustomersByAmount();
    public List<Object[]> getRecentFiveMonthRevenue();

    public List<Object[]> getRevenueByDayOfWeek();

    
}
