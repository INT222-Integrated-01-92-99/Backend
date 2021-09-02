package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCart;
    @OneToOne(mappedBy = "cart")
    private Account account;
    @OneToMany(mappedBy = "cart")
    private List<CartDetails> cartDetails;

    public Cart(long idCart, Account account, List<CartDetails> cartDetails) {
        this.idCart = idCart;
        this.account = account;
        this.cartDetails = cartDetails;
    }

    public Cart() {

    }

    public long getIdCart() {
        return idCart;
    }

    public void setIdCart(long idCart) {
        this.idCart = idCart;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
