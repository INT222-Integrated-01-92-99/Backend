package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "prowithcolors")
public class Prowithcolors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProWithCol")
    private long idprowithcolors;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="idColor")
    private Color color;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="idPro")
    private Product product;

    public long getIdProwithcolors() {
        return idprowithcolors;
    }

    public void setIdProwithcolors(long idprowithcolors) {
        this.idprowithcolors = idprowithcolors;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}