
package FinalEE.Entity;

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
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_type_id",nullable = false)
    private ItemType itemType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_collection_id")
    private ItemCollection itemCollection;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Sale sale;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<ItemImage> imageList;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<StockItem> stockItemList;


}
