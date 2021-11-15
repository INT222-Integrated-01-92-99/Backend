package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import sit.int221.ppclothes.models.CartDetails;
import sit.int221.ppclothes.repositories.repoCartDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CartDetailsController {
    @Autowired
    private repoCartDetails repoCartDetails;

//    @GetMapping("/cartdetails")
//    public List<CartDetails> CartDetailsAll(){
//        return repoCartDetails.findAll();
//    }

}
