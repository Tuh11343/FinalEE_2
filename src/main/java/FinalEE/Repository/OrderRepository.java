
package FinalEE.Repository;

import FinalEE.Entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<ItemOrder, Integer>{
}
