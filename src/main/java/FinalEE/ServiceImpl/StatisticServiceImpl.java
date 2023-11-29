package FinalEE.ServiceImpl;

import FinalEE.Entity.Account;
import FinalEE.Repository.AccountRepository;
import FinalEE.Repository.StatisticRepository;
import FinalEE.Service.AccountService;
import FinalEE.Service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    public StatisticServiceImpl(){

    }

    @Override
    public List<Object[]> productsSoldByMonth(int month,int year) {
        return statisticRepository.productsSoldByMonth(month,year);
    }

    @Override
    public List<Object[]> productsSoldByYear(int year) {
        return statisticRepository.productsSoldByYear(year);
    }

    @Override
    public List<Object[]> totalProductsSoldByMonth(int month, int year) {
        return statisticRepository.totalProductsSoldByMonth(month,year);
    }

    @Override
    public List<Object[]> totalProductsSoldByYear(int year) {
        return statisticRepository.totalProductsSoldByYear(year);
    }

    @Override
    public List<Object[]> getRevenueByMonth(int month,int year) {
        return statisticRepository.getRevenueByMonth(month,year);
    }

    @Override
    public List<Object[]> getRecentFiveMonthRevenue() {
        return statisticRepository.getRecentFiveMonthRevenue();
    }


}
