package FinalEE.ServiceImpl;

import FinalEE.Entity.Sale;
import FinalEE.Repository.SaleRepository;
import FinalEE.Service.SaleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaleServiceImpl implements SaleService{
    
    @Autowired
    private SaleRepository saleRepository;

    public SaleServiceImpl() {
        
    }
    
    
    
    @Override
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
        
    }

    @Override
    public Sale update(Sale sale, int id) {
        
        Sale saleGet=saleRepository.findById(id).orElse(null);
        
        return saleRepository.save(saleGet);
    }

    @Override
    public void delete(Sale sale) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
