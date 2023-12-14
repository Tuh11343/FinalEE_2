
package FinalEE.Service;

import FinalEE.Entity.Cart;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CartService {
    
    boolean create(Cart cart);
    boolean deleteByID(int cartID);
    boolean deleteAllByCustomerID(Integer customerID);
    Cart getCart(int id);
    Cart findCartByItemIDAndCustomerID(int stockItemID,Integer customerID);
    List<Cart> findByCustomerID(Integer customerID);
    List<Cart> getAllCart();

    List<Cart> findAll(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Cart> findByCustomerID(Integer customerID, String sort, ItemServiceImpl.SortOrder sortOrder);

    
}
