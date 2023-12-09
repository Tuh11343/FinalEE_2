
package FinalEE.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_type_id",nullable = false)
    private ItemType itemType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_collection_id")
    private ItemCollection itemCollection;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_material_id",nullable = false)
    private ItemMaterial itemMaterial;

    @Column(name = "description")
    private String description;
    @Column(name = "is_new")
    private int is_new;
    @Column(name = "is_hot")
    private int is_hot;
    @Column(name = "price")
    private double price;
    @Column(name = "year_produce")
    private int year_produce;

    @JsonManagedReference
    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Sale sale;

    @JsonManagedReference
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ItemImage> imageList;

    @JsonBackReference
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<StockItem> stockItemList;


}
