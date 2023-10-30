package FinalEE.ServiceImpl;

import FinalEE.Entity.Cart;
import FinalEE.Repository.CartRepository;
import FinalEE.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl() {

    }

    @Override
    public boolean create(Cart cart) {
        try {
            // Kiểm tra xem cart có tồn tại trong database hay không
            Optional<Cart> existingCart = cartRepository.findById(cart.getId());

            // Lưu cart và kiểm tra kết quả
            cartRepository.save(cart);
            if (existingCart.isPresent()) {
                System.out.println("Cap nhat thanh cong cart:" + cart.getId());
            } else {
                System.out.println("Them thanh cong cart:" + cart.getId());
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
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

}
