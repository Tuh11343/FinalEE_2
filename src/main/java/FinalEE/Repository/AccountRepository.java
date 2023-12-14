
package FinalEE.Repository;

import FinalEE.Entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{


    public List<Account> findAll(Sort sort);
    public List<Account> findAllByNameContains(String name,Sort sort);

    public Account findByNameAndPassword(String name, String password);
    @Query("select a from Account a where a.id=?1 or a.id IS NULL")
    Account findByID(Integer id);

    Account findByCustomer_Id(Integer id);

}
