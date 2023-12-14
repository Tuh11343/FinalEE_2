
package FinalEE.Repository;


import FinalEE.Entity.Cart;
import FinalEE.Entity.Item;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    @Query("SELECT c FROM Cart c WHERE c.stockItem.id = ?1 AND (c.customer.id = ?2 OR (?2 IS NULL AND c.customer.id IS NULL))")
    Cart findByItemIDAndCustomerID(int stockItemID,Integer customerID);

    @Query("SELECT c FROM Cart c where c.customer.id=?1 or c.customer.id IS NULL")
    List<Cart> findByCustomerID(Integer customerID);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.customer.id = ?1 or c.customer.id IS NULL")
    void deleteAllByCustomerID(Integer customerID);

    List<Cart> findByCustomer_Id(Integer customerID, Sort sort);

    List<Cart> findAll(Sort sort);

}
