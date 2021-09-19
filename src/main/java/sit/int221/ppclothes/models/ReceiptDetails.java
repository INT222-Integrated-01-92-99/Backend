package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "receiptdetails" )
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptDetails;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idPro")
    private Product product;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idReceipt")
    private Receipt receipt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idColor")
    private Color idColor;
    private String proName;
    private double proPrice;
    private long proPerPiece;

    public ReceiptDetails(long idReceiptDetails, Product product, Receipt receipt, Color idColor, String proName, double proPrice, long proPerPiece) {
        this.idReceiptDetails = idReceiptDetails;
        this.product = product;
        this.receipt = receipt;
        this.idColor = idColor;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proPerPiece = proPerPiece;
    }

    public ReceiptDetails() {
    }

    public long getIdReceiptDetails() {
        return idReceiptDetails;
    }

    public void setIdReceiptDetails(long idReceiptDetails) {
        this.idReceiptDetails = idReceiptDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Color getIdColor() {
        return idColor;
    }

    public void setIdColor(Color idColor) {
        this.idColor = idColor;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public long getProPerPiece() {
        return proPerPiece;
    }

    public void setProPerPiece(long proPerPiece) {
        this.proPerPiece = proPerPiece;
    }
}
