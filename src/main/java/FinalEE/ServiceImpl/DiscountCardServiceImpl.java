package FinalEE.ServiceImpl;

import FinalEE.Entity.DiscountCard;
import FinalEE.Repository.DiscountCardRepository;
import FinalEE.Service.DiscountCardService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DiscountCardServiceImpl implements DiscountCardService{
    
    @Autowired
    private DiscountCardRepository discountCardRepository;

    public DiscountCardServiceImpl() {
        
    }
    
    
    
    @Override
    public DiscountCard findByID(Integer id) {
        try{
            return discountCardRepository.findByID(id);
        }catch (Exception er){
            System.out.println(er.toString());
        }
        return null;
    }

    @Override
    public List<DiscountCard> getAllDiscountCard() {
        return discountCardRepository.findAll();
    }

    @Override
    public List<DiscountCard> findByCustomerID(Integer customerID) {
        return discountCardRepository.findByCustomerID(customerID);
    }

    @Override
    public List<DiscountCard> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return discountCardRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountCard> findAllByNameLikeSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return discountCardRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountCard> findByCustomerID(Integer id, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return discountCardRepository.findByCustomer_Id(id,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
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
            discountCardRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    
    
}
