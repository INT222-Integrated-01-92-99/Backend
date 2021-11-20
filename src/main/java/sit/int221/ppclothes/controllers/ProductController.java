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
    StorageService storageService;

    @GetMapping("/main/product")
    public List<Product> products(){
        return repoPro.findAll();
    }

    @PostMapping("/staff/add")
    public Product add(@RequestBody Product newproduct){
        if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null && repoPro.findByProName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " AND Name : "+newproduct.getProName() + " Have Already");
        }else if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ID_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " Have Already");
        }else if(repoPro.findByProName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Name : "+newproduct.getProName() + " Have Already");
        }
        Product Productnoitem = new Product(newproduct.getIdPro(), newproduct.getProName(),newproduct.getProDescript(),newproduct.getProPrice(),newproduct.getProAmount(),newproduct.getProMfd(),newproduct.getProPathImg(),newproduct.getBrand());
        repoPro.save(Productnoitem);
        List<Prowithcolors> prowithcolors = newproduct.getProwithcolor();
        for(Prowithcolors prowithcolors1 : prowithcolors){
            prowithcolors1.setProduct(newproduct);
            repoProwithcolos.save(prowithcolors1);
        }
        return repoPro.save(newproduct);
    }

    @PostMapping(value = "/staff/add/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product addProductWithImage(@RequestParam(value = "image",required = false) MultipartFile imageFile,@RequestPart Product newproduct){
        if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null && repoPro.findByProName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " AND Name : "+newproduct.getProName() + " Have Already");
        }else if(repoPro.findById(newproduct.getIdPro()).orElse(null) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_ID_ALREADY_EXIST,"Id : "+newproduct.getIdPro() + " Have Already");
        }else if(repoPro.findByProName(newproduct.getProName()) != null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Name : "+newproduct.getProName() + " Have Already");
        }else if(imageFile == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_IMAGE_NULL,"Can't add. Product id: "+newproduct.getIdPro());
        }else newproduct.setProPathImg(storageService.store(imageFile,newproduct.getIdPro()));
        Product Productitem = new Product(newproduct.getIdPro(), newproduct.getProName(),newproduct.getProDescript(),newproduct.getProPrice(),newproduct.getProAmount(),newproduct.getProMfd(),newproduct.getProPathImg(),newproduct.getBrand());
        repoPro.save(Productitem);
        List<Prowithcolors> prowithcolors = newproduct.getProwithcolor();
        for(Prowithcolors prowithcolors1 : prowithcolors){
            prowithcolors1.setProduct(newproduct);
            repoProwithcolos.save(prowithcolors1);
        }
        return repoPro.save(newproduct);
    }

    @DeleteMapping("/staff/delete/{idPro}")
    public void delete(@PathVariable Long idPro){
        Product product = repoPro.findById(idPro).orElse(null);
        if(product == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't delete. Id : "+idPro + " does not exist.");
        }else storageService.delete(product.getProPathImg());
        List<Prowithcolors> beforeEditProduct = product.getProwithcolor();
        for(Prowithcolors prowithcolors : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors.getIdprowithcolors());
        }
        repoPro.deleteById(idPro);
    }

    @PutMapping("/staff/edit")
    public Product edit(@RequestPart Product editProduct){
        Product productId = repoPro.findById(editProduct.getIdPro()).orElse(null);
        Product productName = repoPro.findByProName(editProduct.getProName());
        if(productId == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't edit. Id : "+editProduct.getIdPro() + " does not exist.");
        }else if(productName != null && productId.getIdPro() != productName.getIdPro()){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Can't edit . Name : "+editProduct.getProName() + " already exist.");
        }
        List<Prowithcolors> beforeEditProduct = productId.getProwithcolor();
        List<Prowithcolors> prowithcolors = editProduct.getProwithcolor();
        for(Prowithcolors prowithcolors1 : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors1.getIdprowithcolors());
        }
        for(Prowithcolors prowithcolors2 : prowithcolors){
            prowithcolors2.setProduct(editProduct);
            repoProwithcolos.save(prowithcolors2);
        }
        editProduct.setProwithcolor(prowithcolors);
        return repoPro.save(editProduct);
    }

    @PutMapping("/staff/edit/image")
    public Product editWithImage(@RequestParam(value = "image",required = false) MultipartFile imageFile, @RequestPart Product editProduct){
        Product productId = repoPro.findById(editProduct.getIdPro()).orElse(null);
        Product productName = repoPro.findByProName(editProduct.getProName());
        if(productId == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_DOES_NOT_EXIST,"Can't edit. Id : "+editProduct.getIdPro() + " does not exist.");
        }else if(productName != null && productId.getIdPro() != productName.getIdPro()){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_NAME_ALREADY_EXIST,"Can't edit . Name : "+editProduct.getProName() + " already exist.");
        }else if(imageFile == null){
            throw new ProductException(ExceptionRepo.ERROR_CODE.PRODUCT_IMAGE_NULL,"Can't edit. Id :"+editProduct.getIdPro());
        }
        storageService.delete(productId.getProPathImg());
        editProduct.setProPathImg(storageService.store(imageFile,editProduct.getIdPro()));
        List<Prowithcolors> beforeEditProduct = productId.getProwithcolor();
        for(Prowithcolors prowithcolors1 : beforeEditProduct){
            repoProwithcolos.deleteById(prowithcolors1.getIdprowithcolors());
        }
        Product Productitem = new Product(editProduct.getIdPro(), editProduct.getProName(),editProduct.getProDescript(),editProduct.getProPrice(),editProduct.getProAmount(),editProduct.getProMfd(),editProduct.getProPathImg(),editProduct.getBrand());
        repoPro.save(Productitem);
        List<Prowithcolors> prowithcolors = editProduct.getProwithcolor();
        for(Prowithcolors prowithcolors2 : prowithcolors){
            prowithcolors2.setProduct(editProduct);
            repoProwithcolos.save(prowithcolors2);
        }
        return repoPro.save(editProduct);
    }

    @GetMapping("/main/product/{idPro}")
    public Product productwihtid(@PathVariable Long idPro){
        Product product = repoPro.findById(idPro).orElse(null);
        return product;
    }

    @GetMapping("/main/getmaxidPro")
    public long maxIdproduct() {
        return repoPro.getMaxproId();
    }

    @GetMapping("/main/search")
    public List<Product> searchproduct(@RequestParam String proname,@RequestParam Long idbrand) {
        return repoPro.searchwithoutbrand(proname);
    }

}
