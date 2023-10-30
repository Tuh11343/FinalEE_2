package FinalEE.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Cart {

    @Id
    private Integer id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "item_color")
    private String item_color;
    @Column(name = "item_size")
    private String item_size;
    @Column(name = "amount")
    private int amount;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", item_id=" + item_id +
                ", item_color='" + item_color + '\'' +
                ", item_size='" + item_size + '\'' +
                ", amount=" + amount +
                '}';
    }
}
