
package FinalEE.Repository;

import FinalEE.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    /*@Query("SELECT ac from Account ac where ac.name=?1 and ac.password=?2")*/
    public Account findByNameAndPassword(String name,String password);
}
