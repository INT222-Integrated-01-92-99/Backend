package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table( name = "cartDetails")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCartDetail;
    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idCart")
    private Cart cart;
    @JoinColumn(name = "proPerPiece")
    private long proPerPiece;
    @JoinColumn(name = "totalPrice")
    private long totalPrice;
    @ManyToOne
    @JoinColumn(name = "idColor")
    private Color color;

    public CartDetails(Product product, Cart cart, long proPerPiece,long totalPrice, Color color) {
        this.product = product;
        this.cart = cart;
        this.proPerPiece = proPerPiece;
        this.totalPrice = totalPrice;
        this.color = color;
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

    public long getProPerPiece() {
        return proPerPiece;
    }

    public void setProPerPiece(long proPerPiece) {
        this.proPerPiece = proPerPiece;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
