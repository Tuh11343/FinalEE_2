package FinalEE.ServiceImpl;

import FinalEE.Entity.DiscountCard;
import FinalEE.Repository.DiscountCardRepository;
import FinalEE.Service.DiscountCardService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiscountCardServiceImpl implements DiscountCardService{
    
    @Autowired
    private DiscountCardRepository discountCardRepository;

    public DiscountCardServiceImpl() {
        
    }
    
    
    
    @Override
    public DiscountCard getDiscountCard(int id) {
        Optional<DiscountCard> discountCard = discountCardRepository.findById(id);
        return discountCard.orElse(null);
    }

    @Override
    public List<DiscountCard> getAllDiscountCard() {
        return discountCardRepository.findAll();
    }

    @Override
    public boolean create(DiscountCard discountCard) {
        try {
            // Kiểm tra xem discountCard có tồn tại trong database hay không
            Optional<DiscountCard> existingDiscountCard = discountCardRepository.findById(discountCard.getId());

            // Lưu discountCard và kiểm tra kết quả
            discountCardRepository.save(discountCard);
            if (existingDiscountCard.isPresent()) {
                System.out.println("Cap nhat thanh cong discountCard:" + discountCard.getId());
            } else {
                System.out.println("Them thanh cong discountCard:" + discountCard.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (discountCardRepository.existsById(id)) {
            //discountCardRepository.deleteById(discountCardID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }
    
    
    
}
