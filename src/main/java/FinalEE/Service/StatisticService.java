
package FinalEE.Service;

import FinalEE.Entity.Account;

import java.util.List;

public interface StatisticService{

    List<Object[]> productsSoldByMonth(int month);
    
}
