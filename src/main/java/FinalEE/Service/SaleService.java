
package FinalEE.Service;

import FinalEE.Entity.Sale;
import java.util.List;

public interface SaleService {
    
    Sale create(Sale item);
    Sale update(Sale item,int id);
    void delete(Sale item);
    Sale getSale(int id);
    List<Sale> getAllSale();
    
}
