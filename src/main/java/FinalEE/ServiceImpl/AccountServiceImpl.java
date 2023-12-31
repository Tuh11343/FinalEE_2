package FinalEE.ServiceImpl;

import FinalEE.Entity.Account;
import FinalEE.Entity.Customer;
import FinalEE.Repository.AccountRepository;
import FinalEE.Service.AccountService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            Account existingAccount=findByID(account.getId());

            // Lưu account và kiểm tra kết quả
            accountRepository.save(account);
            if (existingAccount!=null) {
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
            accountRepository.deleteById(accountID);
            return true;
        }
        return false;

    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account findByID(Integer accountID) {
        try{

            return accountRepository.findByID(accountID);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Account findByNameAndPassword(String name, String password) {
        return accountRepository.findByNameAndPassword(name,password);
    }

    @Override
    public List<Account> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return accountRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> findAllByNameContainsSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return accountRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Account findByCustomerID(Integer customerID) {
        try{
            return accountRepository.findByCustomer_Id(customerID);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Account findByName(String name) {
        try{
            return accountRepository.findByName(name);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

}
