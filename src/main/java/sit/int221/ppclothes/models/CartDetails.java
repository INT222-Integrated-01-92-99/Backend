package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table( name = "cartdetails")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCartDetail;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idCart")
    private Cart cart;

    public CartDetails(long idCartDetail, Product product, Cart cart) {
        this.idCartDetail = idCartDetail;
        this.product = product;
        this.cart = cart;
    }

    public CartDetails() {

    }

    public long getIdCartDetail() {
        return idCartDetail;
    }

    public void setIdCartDetail(long idCartDetail) {
        this.idCartDetail = idCartDetail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
