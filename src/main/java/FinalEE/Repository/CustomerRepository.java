
package FinalEE.Repository;

import FinalEE.Entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    @Query("select c from Customer c where c.id=?1 or c.id IS NULL")
    Customer findByID(Integer id);

    List<Customer> findAll(Sort sort);
    List<Customer> findAllByNameLike(String name,Sort sort);
    Customer findById(int id);

}
