package sit.int221.ppclothes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;

import sit.int221.ppclothes.models.Product;
import sit.int221.ppclothes.models.Prowithcolors;

import sit.int221.ppclothes.repositories.repoProduct;
import sit.int221.ppclothes.repositories.repoProwithcolos;
import sit.int221.ppclothes.repositories.repoBrand;

import sit.int221.ppclothes.services.StorageService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private repoProduct repoPro;
    @Autowired
    private repoProwithcolos repoProwithcolos;
    @Autowired
    private repoBrand repoBrand;
    @Autowired
    StorageService storageService;

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
        Product Productnoitem = new Product(newproduct.getIdPro(), newproduct.getProName(),newproduct.getProDescript(),newproduct.getProPrice(),newproduct.getProMfd(),newproduct.getProPathImg(),newproduct.getBrand());
        repoPro.save(Productnoitem);
        List<Prowithcolors> prowithcolors = newproduct.getProwithcolorList();
        for(Prowithcolors prowithcolors1 : prowithcolors){
            prowithcolors1.setProduct(newproduct);
            repoProwithcolos.save(prowithcolors1);
        }
        return repoPro.save(newproduct);
    }

    @PostMapping(value = "/add/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProductWithImage(@RequestParam(value = "image",required = false) MultipartFile imageFile,@RequestPart Product newproduct){
        if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null && repoPro.findByName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " AND Name : "+newproduct.getProName() + " Have Already");
        }else if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ID_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " Have Already");
        }else if(repoPro.findByName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Name : "+newproduct.getProName() + " Have Already");
        }else if(imageFile == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.ICECREAM_IMAGE_NULL,"Can't add. Product id: "+newproduct.getIdPro());
        }else newproduct.setProPathImg(storageService.store(imageFile,newproduct.getIdPro()));
        Product Productitem = new Product(newproduct.getIdPro(), newproduct.getProName(),newproduct.getProDescript(),newproduct.getProPrice(),newproduct.getProMfd(),newproduct.getProPathImg(),newproduct.getBrand());
        repoPro.save(Productitem);
        List<Prowithcolors> prowithcolors = newproduct.getProwithcolorList();
        for(Prowithcolors prowithcolors1 : prowithcolors){
            prowithcolors1.setProduct(newproduct);
            repoProwithcolos.save(prowithcolors1);
        }
        return repoPro.save(newproduct);
    }

    @DeleteMapping("/delete/{idPro}")
    public void delete(@PathVariable Long idPro){
        Product product = repoPro.findById(idPro).orElse(null);
        if(product == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't delete. Id : "+idPro + " does not exist.");
        }else storageService.delete(product.getProPathImg());
        List<Prowithcolors> beforeEditProduct = product.getProwithcolorList();
        for(Prowithcolors prowithcolors : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors.getIdProwithcolors());
        }
        repoPro.deleteById(idPro);
    }

    @PutMapping("/edit")
    public Product edit(@RequestBody Product editProduct){
        Product productId = repoPro.findById(editProduct.getIdPro()).orElse(null);
        Product productName = repoPro.findByName(editProduct.getProName());
        if(productId == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't edit. Id : "+editProduct.getIdPro() + " does not exist.");
        }else if(productName != null && productId.getIdPro() != productName.getIdPro()){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Can't edit . Name : "+editProduct.getProName() + " already exist.");
        }
        List<Prowithcolors> beforeEditProduct = productId.getProwithcolorList();
        for(Prowithcolors prowithcolors1 : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors1.getIdProwithcolors());
        }
        List<Prowithcolors> prowithcolors = editProduct.getProwithcolorList();
        for(Prowithcolors prowithcolors2 : prowithcolors){
            prowithcolors2.setProduct(editProduct);
            repoProwithcolos.save(prowithcolors2);
        }
        return repoPro.save(editProduct);
    }

    @PutMapping("/edit/image")
    public Product editWithImage(@RequestParam(value = "image",required = false) MultipartFile imageFile, @RequestPart Product editProduct){
        Product productId = repoPro.findById(editProduct.getIdPro()).orElse(null);
        Product productName = repoPro.findByName(editProduct.getProName());
        if(productId == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't edit. Id : "+editProduct.getIdPro() + " does not exist.");
        }else if(productName != null && productId.getIdPro() != productName.getIdPro()){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Can't edit . Name : "+editProduct.getProName() + " already exist.");
        }else if(imageFile == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.ICECREAM_IMAGE_NULL,"Can't edit. Id :"+editProduct.getIdPro());
        }
        storageService.delete(productId.getProPathImg());
        editProduct.setProPathImg(storageService.store(imageFile,editProduct.getIdPro()));
        List<Prowithcolors> beforeEditProduct = productId.getProwithcolorList();
        for(Prowithcolors prowithcolors1 : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors1.getIdProwithcolors());
        }
        Product Productitem = new Product(editProduct.getIdPro(), editProduct.getProName(),editProduct.getProDescript(),editProduct.getProPrice(),editProduct.getProMfd(),editProduct.getProPathImg(),editProduct.getBrand());
        repoPro.save(Productitem);
        List<Prowithcolors> prowithcolors = editProduct.getProwithcolorList();
        for(Prowithcolors prowithcolors2 : prowithcolors){
            prowithcolors2.setProduct(editProduct);
            repoProwithcolos.save(prowithcolors2);
        }
        return repoPro.save(editProduct);
    }

    @GetMapping("/product/{idPro}")
    public Product productwihtid(@PathVariable Long idPro){
        Product product = repoPro.findById(idPro).orElse(null);
        return product;
    }

    @GetMapping("/getmaxidPro")
    public long maxIdproduct() {
        return repoPro.getMaxproId();
    }

    @GetMapping("/search")
    public List<Product> searchproduct(@RequestParam String proname,@RequestParam Long idbrand) {
        return repoPro.searchwithoutbrand(proname);
    }

}