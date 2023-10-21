package FinalEE.ServiceImpl;

import FinalEE.Entity.Account;
import FinalEE.Repository.AccountRepository;
import FinalEE.Service.AccountService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl() {

    }

    @Override
    public boolean create(Account account) {
        try {
            // Kiểm tra xem account có tồn tại trong database hay không
            Optional<Account> existingAccount = accountRepository.findById(account.getId());

            // Lưu account và kiểm tra kết quả
            accountRepository.save(account);
            if (existingAccount.isPresent()) {
                System.out.println("Cap nhat thanh cong account:" + account.getId());
            } else {
                System.out.println("Them thanh cong account:" + account.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteByID(int accountID) {
        if (accountRepository.existsById(accountID)) {
            //accountRepository.deleteById(accountID);
            System.out.println("Ban da xoa:" + accountID);
            return true;
        }
        return false;

    }

    @Override
    public Account getAccount(int id) {
        try {
            Optional<Account> account = accountRepository.findById(id);
            if (account.get() != null) {
                return account.get();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

}
