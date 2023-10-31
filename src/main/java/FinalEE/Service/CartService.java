
package FinalEE.Service;

import FinalEE.Entity.Cart;
import java.util.List;

public interface CartService {
    
    boolean create(Cart cart);
    boolean deleteByID(int cartID);
    Cart getCart(int id);
    Cart findCartByItemIDAndCustomerID(int stockItemID,Integer customerID);
    List<Cart> getAllCart();
    
}
