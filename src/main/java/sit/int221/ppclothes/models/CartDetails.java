package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table( name = "cartdetails")
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
    @JoinColumn(name = "piecePerOnePro")
    private long piecePerOnePro;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idColor")
    private Color idColor;

    public CartDetails(long idCartDetail, Product product, Cart cart, long piecePerOnePro, Color idColor) {
        this.idCartDetail = idCartDetail;
        this.product = product;
        this.cart = cart;
        this.piecePerOnePro = piecePerOnePro;
        this.idColor = idColor;
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

    public long getPiecePerOnePro() {
        return piecePerOnePro;
    }

    public void setPiecePerOnePro(long piecePerOnePro) {
        this.piecePerOnePro = piecePerOnePro;
    }

    public Color getIdColor() {
        return idColor;
    }

    public void setIdColor(Color idColor) {
        this.idColor = idColor;
    }
}
