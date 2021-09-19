package sit.int221.ppclothes.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<Prowithcolors> prowithcolorsList;
    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<CartDetails> cartDetails;
    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<ReceiptDetails> receiptDetails;

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

    public List<Prowithcolors> getProwithcolorsList() {
        return prowithcolorsList;
    }

    public void setProwithcolorsList(List<Prowithcolors> prowithcolorsList) {
        this.prowithcolorsList = prowithcolorsList;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public List<ReceiptDetails> getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(List<ReceiptDetails> receiptDetails) {
        this.receiptDetails = receiptDetails;
    }
}