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
    @OneToMany(mappedBy = "cart")
    private List<CartDetails> cartDetails;

    public Cart(long idCart, List<CartDetails> cartDetails) {
        this.idCart = idCart;
        this.cartDetails = cartDetails;
    }

    public Cart(long idCart) {
        this.idCart = idCart;
    }

    public Cart() {

    }

    public long getIdCart() {
        return idCart;
    }

    public void setIdCart(long idCart) {
        this.idCart = idCart;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
