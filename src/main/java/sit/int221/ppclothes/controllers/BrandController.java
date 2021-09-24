package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import sit.int221.ppclothes.models.Brand;
import sit.int221.ppclothes.repositories.repoBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class BrandController {
    @Autowired
    private repoBrand repoBrand;

    @GetMapping ("/brand")
    public List<Brand> brand(){
        return repoBrand.findAll();
    }

    @GetMapping ("/selectbrand")
    public Brand selectbrand(@RequestParam long brand){
        return repoBrand.selectbrandwithid(brand);
    }
}
