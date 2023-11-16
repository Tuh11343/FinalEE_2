
package FinalEE.Repository;

import FinalEE.Entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<ItemOrder, Integer>{

    @Query("select o from ItemOrder o where o.id=?1")
    ItemOrder findByID(Integer id);

    List<ItemOrder> findAllByCustomer_Id(Integer customerID);
}
