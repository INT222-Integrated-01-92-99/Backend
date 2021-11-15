package sit.int221.ppclothes.controllers;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.models.Brand;
import sit.int221.ppclothes.repositories.repoBrand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@RestController
public class BrandController {
    @Autowired
    private repoBrand repoBrand;

    @GetMapping ("/main/brand")
    public List<Brand> brand(){
        return repoBrand.findAll(Sort.by(Sort.Direction.ASC, "idBrand" ));
    }

    @GetMapping ("/main/selectbrand")
    public Brand selectbrand(@RequestParam long brand){
        return repoBrand.selectbrandwithid(brand);
    }

    @PostMapping(value =  "/admin/addbrand")
    public Brand AddBrand(@RequestParam String BrandName){
        long NewBrandId = repoBrand.getMaxId() + 1;
        Brand NewBrand = new Brand(NewBrandId,BrandName);
        return repoBrand.save(NewBrand);
    }

    @PutMapping(value = "/admin/editbrand")
    public Brand EditBrand(@RequestParam long IdBrand , @RequestParam String BrandName){
        Brand brand = repoBrand.findById(IdBrand).orElse(null);
        brand.setBrandName(BrandName);
        return repoBrand.save(brand);
    }

    @DeleteMapping(value = "/admin/deletebrand")
    public void DeleteBrand(@RequestParam long IdBrand){
        repoBrand.deleteById(IdBrand);
    }
}
