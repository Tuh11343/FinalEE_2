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
    public List<Object[]> getTop10BestSellingProducts() {
        try{
            return statisticRepository.getTop10BestSellingProducts();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getTop5CustomersByRevenue() {
        try{
            return statisticRepository.getTop5CustomersByRevenue();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getTop5CustomersByAmount() {
        try{
            return statisticRepository.getTop5CustomersByAmount();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getRecentFiveMonthRevenue() {
        try{
            return statisticRepository.getRecentFiveMonthRevenue();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Object[]> getRevenueByDayOfWeek() {
        try{
            return statisticRepository.getRevenueByDayOfWeek();
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }
}
