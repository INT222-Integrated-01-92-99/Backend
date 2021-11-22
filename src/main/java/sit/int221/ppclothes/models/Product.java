package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPro;
    private String proName;
    private String proDescript;
    private long proPrice;
    private long proAmount;
    private java.sql.Date proMfd;
    private String proPathImg;
    @ManyToOne
    @JoinColumn(name = "idBrand")
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<Prowithcolors> prowithcolor;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartDetails> cartDetailsList;


    public Product(long idPro, String proName, String proDescript, long proPrice, long proAmount, Date proMfd, String proPathImg, Brand brand) {
        this.idPro = idPro;
        this.proName = proName;
        this.proDescript = proDescript;
        this.proPrice = proPrice;
        this.proAmount = proAmount;
        this.proMfd = proMfd;
        this.proPathImg = proPathImg;
        this.brand = brand;
    }

    public Product() {

    }

    public long getIdPro() {
        return idPro;
    }

    public void setIdPro(long idPro) {
        this.idPro = idPro;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescript() {
        return proDescript;
    }

    public void setProDescript(String proDescript) {
        this.proDescript = proDescript;
    }

    public long getProPrice() {
        return proPrice;
    }

    public void setProPrice(long proPrice) {
        this.proPrice = proPrice;
    }

    public long getProAmount() {
        return proAmount;
    }

    public void setProAmount(long proAmount) {
        this.proAmount = proAmount;
    }

    public Date getProMfd() {
        return proMfd;
    }

    public void setProMfd(Date proMfd) {
        this.proMfd = proMfd;
    }

    public String getProPathImg() {
        return proPathImg;
    }

    public void setProPathImg(String proPathImg) {
        this.proPathImg = proPathImg;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Prowithcolors> getProwithcolor() {
        return prowithcolor;
    }

    public void setProwithcolor(List<Prowithcolors> prowithcolor) {
        this.prowithcolor = prowithcolor;
    }

    public List<CartDetails> getCartDetailsList() {
        return cartDetailsList;
    }

    public void setCartDetailsList(List<CartDetails> cartDetailsList) {
        this.cartDetailsList = cartDetailsList;
    }

}