
package FinalEE.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "itemorder")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_card_id")
    private DiscountCard discountCard;

    @Column(name = "total")
    private double total;
    @Column(name = "date_purchase")
    private Date date_purchase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_status_id")
    private OrderStatus order_status;

    @Column(name = "address")
    private String address;
    @Column(name = "note")
    private String note;
    @Column(name= "email")
    private String email;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetailList;

    public int totalItemAmount(){
        int total=0;
        for(OrderDetail orderDetail:orderDetailList){
            total+=orderDetail.getAmount();
        }
        return total;
    }

}
