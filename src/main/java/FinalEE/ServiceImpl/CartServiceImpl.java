package FinalEE.ServiceImpl;

import FinalEE.Entity.Cart;
import FinalEE.Entity.StockItem;
import FinalEE.Repository.CartRepository;
import FinalEE.Repository.StockItemRepository;
import FinalEE.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private StockItemRepository stockItemRepository;

    public CartServiceImpl() {

    }

    @Override
    public boolean create(Cart cart) {
        try {
            Cart existingCart;
            Integer customerId = cart.getCustomer() != null ? cart.getCustomer().getId() : null;
            existingCart = cartRepository.findByItemIDAndCustomerID(cart.getStockItem().getId(), customerId);

            if (existingCart == null) {
                cartRepository.save(cart);
                System.out.println("Them thanh cong cart:" + cart.getId());
            } else {
                existingCart.setAmount(existingCart.getAmount() + 1);
                cartRepository.save(existingCart);
                System.out.println("Cap nhat thanh cong cart:" + existingCart.getId());
            }

            /*Decrease Stock Item Amount*/
            if (stockItemRepository.findById(cart.getStockItem().getId()).isPresent()) {
                StockItem stockItem = stockItemRepository.findById(cart.getStockItem().getId()).get();
                stockItem.setAmount(stockItem.getAmount() - 1);
                stockItemRepository.save(stockItem);
                System.out.println("Giam so luong thanh cong stockItem:" + stockItem.getId());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean deleteByID(int cartID) {
        if (cartRepository.existsById(cartID)) {
            //cartRepository.deleteById(cartID);
            System.out.println("Ban da xoa:" + cartID);
            return true;
        }
        return false;

    }

    @Override
    public boolean deleteAllByCustomerID(Integer customerID) {
        cartRepository.deleteAllByCustomerID(customerID);
        return cartRepository.findByCustomerID(customerID).isEmpty();
    }

    @Override
    public Cart getCart(int id) {
        try {
            Optional<Cart> cart = cartRepository.findById(id);
            if (cart.get() != null) {
                return cart.get();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Cart findCartByItemIDAndCustomerID(int itemID, Integer customerID) {
        return cartRepository.findByItemIDAndCustomerID(itemID, customerID);
    }

    @Override
    public List<Cart> findByCustomerID(Integer customerID) {
        return cartRepository.findByCustomerID(customerID);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> findAll(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return cartRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cart> findByCustomerID(Integer customerID, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return cartRepository.findByCustomer_Id(customerID,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

}
