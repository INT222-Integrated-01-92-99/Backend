package sit.int221.ppclothes.controllers;

import sit.int221.ppclothes.models.Brand;
import sit.int221.ppclothes.repositories.repoBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")

@RestController
public class BrandController {
    @Autowired
    private repoBrand repoBrand;

    @GetMapping ("/brand")
    public List<Brand> brand(){
        return repoBrand.findAll();
    }

}
