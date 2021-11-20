package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.CartException;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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
    @Autowired
    private repoAccount repoAccount;

    @GetMapping("/admin/cart")
    public List<Cart> CartAll(){
        return repoCart.findAll();
    }

    @GetMapping("/member/cart/{idcart}")
    public Cart CartbyIdCart(@PathVariable Long idcart){
        Cart cart = repoCart.findById(idcart).orElse(null);
        return cart;
    }

    public void checkamount(long amount,long idpro,long idcart,long idcartdetail){
        long amountofpro = repoProduct.amount(idpro);
        if(amount > amountofpro){
            throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your amount more than stock.");
        }else if(amount <= 0){
            throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your amount less than 0.");
        }
        if(repoCartDetails.getTotalInCartOfProduct(idcart,idpro) != null){
            if(repoCartDetails.findById(idcartdetail).orElse(null) == null){
                long totalAmountInCart = repoCartDetails.getTotalInCartOfProduct(idcart,idpro);
                if(totalAmountInCart+amount > amountofpro){
                    throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your total amount in cart more than stock");
                }else if(totalAmountInCart+amount <= 0){
                    throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE,"Your amount less than 0.");
                }
                return;
            }
            if( repoCartDetails.getTotalInCartWithoutsomeId(idcart, idpro,idcartdetail) != null ){
                long totalAmountInCart = repoCartDetails.getTotalInCartWithoutsomeId(idcart, idpro,idcartdetail);
                if (totalAmountInCart + amount > amountofpro) {
                    throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE, "Your total amount in cart more than stock");
                } else if (totalAmountInCart + amount <= 0) {
                    throw new CartException(ExceptionRepo.ERROR_CODE.AMOUNT_VALUE, "Your amount less than 0.");
                }
                return;
            }
        }
    }

    @PostMapping(value = "/member/additemtocart")
    public CartDetails additemtocart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcart") long idcart,@RequestParam(name = "idcolor") long idcolor){
        checkamount(amount,idpro,idcart,0);
        Cart cart = repoCart.findById(idcart).orElse(null);
        Product product = repoProduct.findById(idpro).orElse(null);
        Color color = repoColor.findById(idcolor).orElse(null);
        double totalprice = product.getProPrice() * amount;
        cart.setTotalPrice(cart.getTotalPrice()+totalprice);
        CartDetails newitemincart = new CartDetails(product,cart,amount,totalprice,color);
        return repoCartDetails.save(newitemincart);
    }


    @PutMapping("/member/edititemincart")
    public CartDetails editamountitemincart(@RequestParam(name = "idpro") long idpro,@RequestParam(name = "amount") long amount,@RequestParam(name = "idcartdetail") long idcartdetail,@RequestParam(name = "idcolor") long idcolor){
        long idcart = repoCartDetails.getidcart(idcartdetail);
        Cart cart = repoCart.findById(idcart).orElse(null);
        checkamount(amount,idpro,idcart,idcartdetail);
        CartDetails cartDetails = repoCartDetails.findById(idcartdetail).orElse(null);
        double productprice = cartDetails.getTotalPrice() / cartDetails.getProPerPiece();
        cart.setTotalPrice(cart.getTotalPrice() - cartDetails.getTotalPrice());
        double newtotalprice = productprice * amount;
        cart.setTotalPrice(cart.getTotalPrice() + newtotalprice);
        cartDetails.setProPerPiece(amount);
        cartDetails.setTotalPrice(newtotalprice);
        Color color = repoColor.findById(idcolor).orElse(null);
        cartDetails.setColor(color);
        return repoCartDetails.save(cartDetails);
    }

    @DeleteMapping("/member/deleteitemincart")
    public void deleteitemincart(@RequestParam(name = "idcartdetail") long idcartdetail){
        long idcart = repoCartDetails.getidcart(idcartdetail);
        CartDetails delcartdetail = repoCartDetails.findById(idcartdetail).orElse(null);
        double totalprice = delcartdetail.getTotalPrice();
        Cart cart = repoCart.findById(idcart).orElse(null);
        cart.setTotalPrice(cart.getTotalPrice() - totalprice);
        repoCartDetails.deleteById(idcartdetail);
    }

    @DeleteMapping("/member/deletemultipleitemincart")
    public void deleteMultipleIteminCart(@RequestParam(name = "idcartdetail") long[] idcartdetail){
        for(long idcartdetailPerline : idcartdetail){
            long idcart = repoCartDetails.getidcart(idcartdetailPerline);
            CartDetails delcartdetail = repoCartDetails.findById(idcartdetailPerline).orElse(null);
            double totalprice = delcartdetail.getTotalPrice();
            Cart cart = repoCart.findById(idcart).orElse(null);
            cart.setTotalPrice(cart.getTotalPrice() - totalprice);
            repoCartDetails.deleteById(idcartdetailPerline);
        }
    }

    @PostMapping("/member/purchase")
    public String purchase(@RequestParam(name = "idcart") long idcart){
        long idacc = repoAccount.getidacc(idcart);
        Account account = repoAccount.findById(idacc).orElse(null);
        LocalDateTime time = LocalDateTime.now();
        Cart cart = repoCart.findById(idcart).orElse(null);
        Receipt receipt = new Receipt(account,time,cart.getTotalPrice());
        List<CartDetails> cartDetailsList = repoCartDetails.listcartdetailByidcart(idcart);
        repoReceipt.save(receipt);
        for(CartDetails cartDetailperline : cartDetailsList){
            long idpro = cartDetailperline.getProduct().getIdPro();
            String proname = cartDetailperline.getProduct().getProName();
            String brandname = cartDetailperline.getProduct().getBrand().getBrandName();
            Double proprice = cartDetailperline.getProduct().getProPrice();
            long properpiece = cartDetailperline.getProPerPiece();
            double totalprice = cartDetailperline.getTotalPrice();
            Color color = cartDetailperline.getColor();
            ReceiptDetails newreceiptDetail = new ReceiptDetails(receipt,color,proname,brandname,proprice,properpiece,totalprice);
            repoReceiptDetails.save(newreceiptDetail);
            repoCartDetails.deleteById(cartDetailperline.getIdCartDetail());
            long amountofpro = repoProduct.amount(idpro);
            long newamount = amountofpro - properpiece;
            Product productNewAmount = repoProduct.findById(idpro).orElse(null);
            productNewAmount.setProAmount(newamount);
            repoProduct.save(productNewAmount);
        }
        cart.setTotalPrice(0);
        repoCart.save(cart);
        return "Success";
    }

//    @GetMapping("/test")
//    public List<CartDetails> test(@RequestParam(name = "idcart") long idcart){
//        return repoCartDetails.listcartdetailByidcart(idcart);
//    }
}
