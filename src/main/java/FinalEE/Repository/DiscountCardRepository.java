
package FinalEE.Repository;

import FinalEE.Entity.Cart;
import FinalEE.Entity.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Integer>{

    @Query("SELECT dc FROM DiscountCard dc where dc.customer.id=?1")
    List<DiscountCard> findByCustomerID(Integer customerID);

}
