package FinalEE.ServiceImpl;

import FinalEE.Entity.Sale;
import FinalEE.Repository.SaleRepository;
import FinalEE.Service.SaleService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
            Sale existingSale=saleRepository.findByID(sale.getId());

            // Lưu sale và kiểm tra kết quả
            saleRepository.save(sale);
            if (existingSale!=null) {
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
            saleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return saleRepository.findAll(sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Sale> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder) {
        try{
            Sort sortBy;
            if (sortOrder == ItemServiceImpl.SortOrder.DESC) {
                sortBy = Sort.by(sort).descending();
            } else {
                sortBy = Sort.by(sort).ascending();
            }
            return saleRepository.findAllByNameContains(name,sortBy);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

    @Override
    public Sale findBySale(Integer id) {
        try{
            return saleRepository.findByID(id);
        }catch (Exception er){
            er.printStackTrace();
        }
        return null;
    }

}
