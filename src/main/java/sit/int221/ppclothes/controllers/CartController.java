package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.*;
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

    public void ChangeProductAmount(long amount,long idpro){
        long newamount = 0;
        if(amount<0){
            newamount = repoProduct.amount(idpro) - Math.abs(amount) ;
        }else{
            newamount = repoProduct.amount(idpro) + amount;
        }
        if(newamount<0){
            return;
        }
        Product newproduct = repoProduct.findById(idpro).orElse(null);
        newproduct.setProAmount(newamount);
        repoProduct.save(newproduct);
    }

    @PostMapping(value = "/additemtocart")
    public CartDetails additemtocart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcart") long idcart){
        ChangeProductAmount(0 - amount,idpro);
        Cart cart = repoCart.findById(idcart).orElse(null);
        Product product = repoProduct.findById(idpro).orElse(null);
        CartDetails newitemincart = new CartDetails(product,cart,amount);
        return repoCartDetails.save(newitemincart);
    }


    @PutMapping("/edititemincart")
    public CartDetails editamountitemincart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcartdetail") long idcartdetail){
        CartDetails cartDetails = repoCartDetails.findById(idcartdetail).orElse(null);
        long diffamount = cartDetails.getPiecePerOnePro()-amount;
        cartDetails.setPiecePerOnePro(amount);
        ChangeProductAmount(diffamount,idpro);
        return repoCartDetails.save(cartDetails);
    }

    @DeleteMapping("/deleteitemincart")
    public void deleteitemincart(@RequestParam(name = "idcartdetail") long idcartdetail,@RequestParam(name = "idpro") long idpro){
        CartDetails cartDetails = repoCartDetails.findById(idcartdetail).orElse(null);
        long returnamount = cartDetails.getPiecePerOnePro();
        ChangeProductAmount(returnamount,idpro);
        repoCartDetails.deleteById(idcartdetail);
    }

//    @GetMapping("/purchase")
//    public List<ReceiptDetails> purchase(@RequestParam(name = "idcart") long idcart){
//        Cart cart = repoCart.findById(idcart).orElse(null);
//        return null;
//    }

}
