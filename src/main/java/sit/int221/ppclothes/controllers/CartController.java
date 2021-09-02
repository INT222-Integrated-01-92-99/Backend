package sit.int221.ppclothes.controllers;

import sit.int221.ppclothes.models.Cart;
import sit.int221.ppclothes.repositories.repoCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private repoCart repoCart;

    @GetMapping("/cart")
    public List<Cart> CartAll(){
        return repoCart.findAll();
    }
}
