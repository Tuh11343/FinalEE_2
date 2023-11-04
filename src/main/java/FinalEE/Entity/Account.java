/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalEE.Entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id",nullable = false)
    private Permission permission;

    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;


}
