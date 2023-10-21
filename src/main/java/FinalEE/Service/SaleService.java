
package FinalEE.Service;

import FinalEE.Entity.Sale;
import java.util.List;

public interface SaleService {
    
    boolean create(Sale sale);
    boolean deleteByID(int id);
    Sale getSale(int id);
    List<Sale> getAllSale();
    
}
