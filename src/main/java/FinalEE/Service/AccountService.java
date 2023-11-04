
package FinalEE.Service;

import FinalEE.Entity.Account;
import java.util.List;

public interface AccountService {
    
    boolean create(Account account);
    boolean deleteByID(int accountID);
    Account getAccount(int id);
    List<Account> getAllAccount();

    Account findByNameAndPassword(String name,String password);
    
}
