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
@Table(name = "itemorderdetail")
public class OrderDetail {
    
    @Id
    private int id;
    @Column(name = "order_id")
    private int order_id;
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "amount")
    private int amount;
    @Column(name = "item_color")
    private String item_color;
    @Column(name = "item_size")
    private String item_size;
    @Column(name = "total")
    private double total;
}
