package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.CartException;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private repoCart repoCart;
    @Autowired
    private repoCartDetails repoCartDetails;
    @Autowired
    private repoProduct repoProduct;
    @Autowired
    private repoColor repoColor;
    @Autowired
    private repoReceipt repoReceipt;
    @Autowired
    private repoReceiptDetails repoReceiptDetails;

    @GetMapping("/cart")
    public List<Cart> CartAll(){
        return repoCart.findAll();
    }

    @GetMapping("/cart/{idcart}")
    public Cart CartbyIdCart(@PathVariable Long idcart){
        Cart cart = repoCart.findById(idcart).orElse(null);
        return cart;
    }

    public void checkamount(long amount,long idpro){
        long amountofpro = repoProduct.amount(idpro);
        if(amount > amountofpro){
            throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your amount more than stock.");
        }else if(amount <= 0){
            throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your amount less than 0.");
        }
    }

    @PostMapping(value = "/additemtocart")
    public CartDetails additemtocart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcart") long idcart,@RequestParam(name = "idcolor") long idcolor){
        checkamount(amount,idpro);
        Cart cart = repoCart.findById(idcart).orElse(null);
        Product product = repoProduct.findById(idpro).orElse(null);
        Color color = repoColor.findById(idcolor).orElse(null);
        CartDetails newitemincart = new CartDetails(product,cart,amount,color);
        return repoCartDetails.save(newitemincart);
    }


    @PutMapping("/edititemincart")
    public CartDetails editamountitemincart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcartdetail") long idcartdetail){
        checkamount(amount,idpro);
        CartDetails cartDetails = repoCartDetails.findById(idcartdetail).orElse(null);
        cartDetails.setPiecePerOnePro(amount);
        return repoCartDetails.save(cartDetails);
    }

    @DeleteMapping("/deleteitemincart")
    public void deleteitemincart(@RequestParam(name = "idcartdetail") long idcartdetail){
        repoCartDetails.deleteById(idcartdetail);
    }

    @GetMapping("/purchase")
    public List<ReceiptDetails> purchase(@RequestParam(name = "idcart") long idcart){
            return null;
    }

}
