package sit.int221.ppclothes.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPro;
    private String proName;
    private String proDescript;
    private double proPrice;
    private long proAmount;
    private java.sql.Date proMfd;
    private String proPathImg;
    @ManyToOne
    @JoinColumn(name = "idBrand")
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<Prowithcolors> prowithcolorList;
    @OneToMany(mappedBy = "product")
    private List<CartDetails> cartDetailsList;
    @OneToMany(mappedBy = "product")
    private List<ReceiptDetails> ReceiptDetailsList;

    public Product(long idPro, String proName, String proDescript, double proPrice, Date proMfd, String proPathImg, Brand brand) {
        this.idPro = idPro;
        this.proName = proName;
        this.proDescript = proDescript;
        this.proPrice = proPrice;
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

    public double getProPrice() {
        return proPrice;
    }

    public void setProPrice(double proPrice) {
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

    public List<Prowithcolors> getProwithcolorList() {
        return prowithcolorList;
    }

    public void setProwithcolorList(List<Prowithcolors> prowithcolorList) {
        this.prowithcolorList = prowithcolorList;
    }

    public List<CartDetails> getCartDetailsList() {
        return cartDetailsList;
    }

    public void setCartDetailsList(List<CartDetails> cartDetailsList) {
        this.cartDetailsList = cartDetailsList;
    }

    public List<ReceiptDetails> getReceiptDetailsList() {
        return ReceiptDetailsList;
    }

    public void setReceiptDetailsList(List<ReceiptDetails> receiptDetailsList) {
        ReceiptDetailsList = receiptDetailsList;
    }
}