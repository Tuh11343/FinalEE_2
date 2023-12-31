
package FinalEE.Service;

import FinalEE.Entity.Account;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface AccountService {
    
    boolean create(Account account);
    boolean deleteByID(int accountID);

    List<Account> getAllAccount();

    Account findByID(Integer accountID);
    Account findByNameAndPassword(String name, String password);
    List<Account> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Account> findAllByNameContainsSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    Account findByCustomerID(Integer customerID);

    Account findByName(String name);
    
}
