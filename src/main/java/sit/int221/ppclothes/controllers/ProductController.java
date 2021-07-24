package sit.int221.ppclothes.controllers;

import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;
import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.models.Product;
import sit.int221.ppclothes.models.Item;
import sit.int221.ppclothes.repositories.repoProduct;
import sit.int221.ppclothes.repositories.repoItem;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProductController {

    @Autowired
    private repoProduct repoPro;
    @Autowired
    private repoItem repoItem;

    @GetMapping("/product")
    public List<Product> products(){
        return repoPro.findAll();
    }

    @PostMapping("/add")
    public Product add(@RequestBody Product newproduct){
        if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null && repoPro.findByName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " AND Name : "+newproduct.getProName() + " Have Already");
        }else if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ID_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " Have Already");
        }else if(repoPro.findByName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Name : "+newproduct.getProName() + " Have Already");
        }
        Product Productitem = new Product(newproduct.getIdPro(),newproduct.getProDescript(),newproduct.getProPrice(),newproduct.getProName(),newproduct.getProPathImg(),newproduct.getBrand());
        repoPro.save(Productitem);
        List<Item> item = newproduct.getItem();
        for(Item item1 : item){
            item1.setProduct(newproduct);
            repoItem.save(item1);
        }
        return repoPro.save(newproduct);
    }

    @DeleteMapping("/delete/{idPro}")
    public void delete(@PathVariable Long idPro){
        Product product = repoPro.findById(idPro).orElse(null);
        if(product == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't delete. Id : "+idPro + " does not exist.");
        }
        repoPro.deleteById(idPro);
    }

    @PutMapping("/edit/{idPro}")
    public Product edit(@RequestBody Product editProduct){
        Product productId = repoPro.findById(editProduct.getIdPro()).orElse(null);
        Product productName = repoPro.findByName(editProduct.getProName());
        if(productId == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't edit. Id : "+editProduct.getIdPro() + " does not exist.");
        }else if(productName != null && productId.getIdPro() != productName.getIdPro()){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Can't edit . Name : "+editProduct.getProName() + " already exist.");
        }
        return repoPro.save(editProduct);
    }

}
