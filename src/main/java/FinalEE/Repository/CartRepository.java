
package FinalEE.Repository;


import FinalEE.Entity.Cart;
import FinalEE.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    @Query("SELECT c FROM Cart c WHERE c.stock_item_id = ?1 AND (c.customer_id = ?2 OR (?2 IS NULL AND c.customer_id IS NULL))")
    Cart findByItemIDAndCustomerID(int stockItemID,Integer customerID);
}
