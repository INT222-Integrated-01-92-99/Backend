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

    public ReceiptDetails(long idReceiptDetails, Product product, Receipt receipt) {
        this.idReceiptDetails = idReceiptDetails;
        this.product = product;
        this.receipt = receipt;
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
}
