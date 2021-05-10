package sit.int221.ppclothes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPro;
    private String proName;
    private String proDescript;
    private double proPrice;
    private java.sql.Date proMfd;
    private String proPathImg;
    @ManyToOne
    @JoinColumn(name = "idBrand")
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<Item> Item;

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


    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
        this.proPrice = proPrice;
    }


    public java.sql.Date getProMfd() {
        return proMfd;
    }

    public void setProMfd(java.sql.Date proMfd) {
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

    public List<sit.int221.ppclothes.models.Item> getItem() {
        return Item;
    }

    public void setItem(List<sit.int221.ppclothes.models.Item> item) {
        Item = item;
    }
}