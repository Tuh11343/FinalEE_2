
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
@Table(name = "customer")
public class Customer {
    
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name="email")
    private String email;
    @Column(name = "address")
    private String address;
}
