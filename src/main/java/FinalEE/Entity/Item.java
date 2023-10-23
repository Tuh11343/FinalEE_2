
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
@Table(name = "item")
public class Item {

    @Id
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "item_type_id")
    private int item_type_id;
    @Column(name = "item_collection_id")
    private Integer item_collection_id;
    @Column(name = "item_material_id")
    private int item_material_id;
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

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", item_type_id=" + item_type_id + ", item_collection_id=" + item_collection_id + ", item_material_id=" + item_material_id + ", is_new=" + is_new + ", is_hot=" + is_hot + ", price=" + price + ", year_produce=" + year_produce + '}';
    }


}
