
package FinalEE.Service;

import FinalEE.Entity.Account;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccountService {
    
    boolean create(Account account);
    boolean deleteByID(int accountID);
    Account getAccount(int id);
    List<Account> getAllAccount();

    Account findByID(Integer accountID);
    Account findByNameAndPassword(String name, String password);
    List<Account> findAllSort(Sort sort, ItemServiceImpl.SortOrder sortOrder);
    
}
