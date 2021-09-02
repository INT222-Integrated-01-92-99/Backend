package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "receiptdetails" )
public class ReceiptDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceiptDetails;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idReceipt")
    private Receipt receipt;
    private String proName;
    private double proPrice;
    private long proPerPiece;
    private String status;

    public ReceiptDetails(long idReceiptDetails, Product product, Receipt receipt, String proName, double proPrice, long proPerPiece, String status) {
        this.idReceiptDetails = idReceiptDetails;
        this.product = product;
        this.receipt = receipt;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proPerPiece = proPerPiece;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
