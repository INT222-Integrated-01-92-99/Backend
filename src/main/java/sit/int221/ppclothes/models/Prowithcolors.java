package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "proWithColors")
public class Prowithcolors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProWithCol")
    private long idprowithcolors;
    @ManyToOne
    @JoinColumn(name="idColor")
    private Color color;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idPro")
    private Product product;

    public long getIdprowithcolors() {
        return idprowithcolors;
    }

    public void setIdprowithcolors(long idprowithcolors) {
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