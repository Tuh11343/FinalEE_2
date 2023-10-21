/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Account {
    
    @Id
    private Integer id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "permission_id")
    private int permission_id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", customer_id=" + customer_id + ", permission_id=" + permission_id + ", name=" + name + ", password=" + password + '}';
    }
    
}
