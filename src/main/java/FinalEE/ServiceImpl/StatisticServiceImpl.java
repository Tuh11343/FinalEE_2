package FinalEE.ServiceImpl;

import FinalEE.Entity.Account;
import FinalEE.Repository.AccountRepository;
import FinalEE.Repository.StatisticRepository;
import FinalEE.Service.AccountService;
import FinalEE.Service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StatisticRepository statisticRepository;

    public StatisticServiceImpl(){

    }

    @Override
    public List<Object[]> productsSoldByMonth(int month) {
        return statisticRepository.productsSoldByMonth(month);
    }
}
