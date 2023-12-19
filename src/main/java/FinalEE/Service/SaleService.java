
package FinalEE.Service;

import FinalEE.Entity.Sale;
import FinalEE.ServiceImpl.ItemServiceImpl;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SaleService {
    
    boolean create(Sale sale);
    boolean deleteByID(int id);
    List<Sale> getAllSale();

    List<Sale> findAllSort(String sort, ItemServiceImpl.SortOrder sortOrder);
    List<Sale> findAllByNameContains(String name, String sort, ItemServiceImpl.SortOrder sortOrder);

    Sale findBySale(Integer id);

    Sale findByItemID(Integer itemID);

    
}
