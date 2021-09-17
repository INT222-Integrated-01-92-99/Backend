package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "receipt" )
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReceipt;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idAccount")
    private Account account;
    @OneToMany(mappedBy = "receipt")
    private List<ReceiptDetails> receiptDetailsList;

    public Receipt(Account account, List<ReceiptDetails> receiptDetailsList) {
        this.account = account;
        this.receiptDetailsList = receiptDetailsList;
    }

    public Receipt() {

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

    public List<ReceiptDetails> getReceiptDetailsList() {
        return receiptDetailsList;
    }

    public void setReceiptDetailsList(List<ReceiptDetails> receiptDetailsList) {
        this.receiptDetailsList = receiptDetailsList;
    }
}
