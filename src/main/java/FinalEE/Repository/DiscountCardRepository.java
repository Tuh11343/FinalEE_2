
package FinalEE.Repository;

import FinalEE.Entity.Cart;
import FinalEE.Entity.Customer;
import FinalEE.Entity.DiscountCard;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer>{

    @Query("SELECT dc FROM DiscountCard dc where dc.customer.id=?1")
    List<DiscountCard> findByCustomerID(Integer customerID);

    @Query("select d from DiscountCard d where d.id=?1 or d.id IS NULL")
    DiscountCard findByID(Integer id);


    List<DiscountCard> findAll(Sort sort);
    List<DiscountCard> findAllByNameContains(String name,Sort sort);
    List<DiscountCard> findByCustomer_Id(Integer customerID,Sort sort);

}
