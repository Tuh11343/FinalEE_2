
package FinalEE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "itemorder")
public class ItemOrder {
    
    @Id
    private int id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "discount_card_id")
    private int discount_card_id;
    @Column(name = "total")
    private double total;
    @Column(name = "date_purchase")
    private Date date_purchase;
}
