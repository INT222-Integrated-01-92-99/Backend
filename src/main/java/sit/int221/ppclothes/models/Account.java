package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;
    private String accUsername;
    private String accPass;
    private String accFname;
    private String accLname;
    private String accPhone;
    private String accAddress;
    @OneToOne(optional = false)
    @JoinColumn(name = "idCart", referencedColumnName = "idCart")
    private Cart cart;
    @OneToMany(mappedBy = "account")
    private List<Receipt> receiptList;
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role idRole;

    public Account(long idAccount, String accUsername, String accPass, String accFname, String accLname, String accPhone, String accAddress, Role idRole) {
        this.idAccount = idAccount;
        this.accUsername = accUsername;
        this.accPass = accPass;
        this.accFname = accFname;
        this.accLname = accLname;
        this.accPhone = accPhone;
        this.accAddress = accAddress;
        this.idRole = idRole;
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

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }
}