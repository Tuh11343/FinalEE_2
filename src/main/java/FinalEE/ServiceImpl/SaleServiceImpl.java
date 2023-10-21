package FinalEE.ServiceImpl;

import FinalEE.Entity.Sale;
import FinalEE.Repository.SaleRepository;
import FinalEE.Service.SaleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaleServiceImpl implements SaleService{
    
    @Autowired
    private SaleRepository saleRepository;

    public SaleServiceImpl() {
        
    }
    
    
    
    @Override
    public boolean create(Sale sale) {
        try {
            // Kiểm tra xem sale có tồn tại trong database hay không
            Optional<Sale> existingSale = saleRepository.findById(sale.getId());

            // Lưu sale và kiểm tra kết quả
            saleRepository.save(sale);
            if (existingSale.isPresent()) {
                System.out.println("Cap nhat thanh cong sale:" + sale.getId());
            } else {
                System.out.println("Them thanh cong sale:" + sale.getId());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByID(int id) {
        if (saleRepository.existsById(id)) {
            //saleRepository.deleteById(saleID);
            System.out.println("Ban da xoa:" + id);
            return true;
        }
        return false;
    }
    

    @Override
    public Sale getSale(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }
    
    
    
}
