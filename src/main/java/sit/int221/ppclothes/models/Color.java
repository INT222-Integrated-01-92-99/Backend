package sit.int221.ppclothes.models;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idColor")
    private long idColor;
    @Column(name = "colorName")
    private String colorName;
    @Column(name = "colorCode")
    private String colorCode;
    @JsonBackReference
    @OneToMany(mappedBy = "color")
    private List<Item> itemList;

    public long getIdColor() {
        return idColor;
    }

    public void setIdColor(long idColor) {
        this.idColor = idColor;
    }


    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }


    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}