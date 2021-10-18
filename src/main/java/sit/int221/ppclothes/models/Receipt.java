package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table( name = "receipt" )
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceipt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idAccount")
    private Account account;
    private LocalDateTime datePurchase;
    private double totalPrice;
    @OneToMany(mappedBy = "receipt")
    private List<ReceiptDetails> receiptDetailsList;

    public Receipt(Account account, LocalDateTime datePurchase, double totalPrice) {
        this.account = account;
        this.datePurchase = datePurchase;
        this.totalPrice = totalPrice;
    }

    public Receipt() {

    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(long idReceipt) {
        this.idReceipt = idReceipt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(LocalDateTime datePurchase) {
        this.datePurchase = datePurchase;
    }

    public List<ReceiptDetails> getReceiptDetailsList() {
        return receiptDetailsList;
    }

    public void setReceiptDetailsList(List<ReceiptDetails> receiptDetailsList) {
        this.receiptDetailsList = receiptDetailsList;
    }
}
