package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;
    private String accUsername;
    private String accPass;
    private String accRole;
    private String accFname;
    private String accLname;
    private String accPhone;
    private String accAddress;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "idCart", referencedColumnName = "idCart")
    private Cart cart;
    @JsonBackReference
    @OneToMany(mappedBy = "account")
    private List<Receipt> receiptList;

    public Account(long idAccount, String accUsername, String accPass, String accRole, String accFname, String accLname, String accPhone, String accAddress, Cart cart, List<Receipt> receiptList) {
        this.idAccount = idAccount;
        this.accUsername = accUsername;
        this.accPass = accPass;
        this.accRole = accRole;
        this.accFname = accFname;
        this.accLname = accLname;
        this.accPhone = accPhone;
        this.accAddress = accAddress;
        this.cart = cart;
        this.receiptList = receiptList;
    }

    public Account() {

    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccUsername() {
        return accUsername;
    }

    public void setAccUsername(String accUsername) {
        this.accUsername = accUsername;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public String getAccRole() {
        return accRole;
    }

    public void setAccRole(String accRole) {
        this.accRole = accRole;
    }

    public String getAccFname() {
        return accFname;
    }

    public void setAccFname(String accFname) {
        this.accFname = accFname;
    }

    public String getAccLname() {
        return accLname;
    }

    public void setAccLname(String accLname) {
        this.accLname = accLname;
    }

    public String getAccPhone() {
        return accPhone;
    }

    public void setAccPhone(String accPhone) {
        this.accPhone = accPhone;
    }

    public String getAccAddress() {
        return accAddress;
    }

    public void setAccAddress(String accAddress) {
        this.accAddress = accAddress;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }
}