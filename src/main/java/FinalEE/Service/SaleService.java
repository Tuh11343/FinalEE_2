
package FinalEE.Service;

import FinalEE.Entity.Sale;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SaleService {
    
    boolean create(Sale sale);
    boolean deleteByID(int id);
    Sale getSale(int id);
    List<Sale> getAllSale();

    List<Sale> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Sale> findAllByNameSort(String name, String sort, ItemServiceImpl.SortOrder sortOrder);
    
}
