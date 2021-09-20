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
    @JoinColumn(name = "idReceipt")
    private Receipt receipt;
    @ManyToOne
    @JoinColumn(name = "idColor")
    private Color color;
    private String proName;
    private String brandName;
    private double proPrice;
    private long proPerPiece;

    public ReceiptDetails(Receipt receipt, Color color, String proName, String brandName, double proPrice, long proPerPiece) {
        this.receipt = receipt;
        this.color = color;
        this.proName = proName;
        this.brandName = brandName;
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
