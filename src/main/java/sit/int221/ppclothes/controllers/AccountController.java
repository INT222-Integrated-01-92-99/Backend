package sit.int221.ppclothes.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.AccountException;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.repoAccount;
import sit.int221.ppclothes.repositories.repoRole;
import sit.int221.ppclothes.repositories.repoCart;
import sit.int221.ppclothes.repositories.repoCartDetails;
import sit.int221.ppclothes.repositories.repoReceipt;
import sit.int221.ppclothes.repositories.repoReceiptDetails;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoCart repoCart;
    @Autowired
    private repoCartDetails repoCartDetails;
    @Autowired
    private repoReceipt repoReceipt;
    @Autowired
    private repoReceiptDetails repoReceiptDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/account")
    public List<Account> Account(){
        return repoAccount.findAll();
    }


    @GetMapping("/admin/account/{idacc}")
    public Account AccountById(@PathVariable long idacc){
        Account account = repoAccount.findById(idacc).orElse(null);
        return account;
    }

    @PostMapping(value = "/main/registaccount")
    public Account Register(@RequestBody Account newAccount){
        if(repoAccount.findByAccUsername(newAccount.getAccUsername()) != null){
            throw new AccountException(ExceptionRepo.ERROR_CODE.USERNAME_HAVE_ALREADY,"Username have already!!");
        }
        long newIDcart = repoCart.getMaxCartId() + 1;
        long newIDaccount = repoAccount.getMaxidAcc() + 1;
        String encodedpassword = passwordEncoder.encode(newAccount.getAccPass());
        Account newAccountnoReceipt = new Account(newIDaccount,newAccount.getAccUsername(),encodedpassword,newAccount.getAccFname(), newAccount.getAccLname(), newAccount.getAccPhone(), newAccount.getAccAddress(),newAccount.getIdRole());
        Cart newCart = new Cart(newIDcart,0);
        repoCart.save(newCart);
        newAccountnoReceipt.setCart(newCart);
        repoAccount.save(newAccountnoReceipt);
        return newAccount;
    }

    @PutMapping(value = "/main/editaccount")
    public Account EditAccount(@RequestBody Account editAccount){
        String encodedpassword = passwordEncoder.encode(editAccount.getAccPass());
        editAccount.setAccPass(encodedpassword);
        return repoAccount.save(editAccount);
    }


    @DeleteMapping(value = "/admin/deleteaccount")
    public void DelAccount(@RequestParam long idAccount){
        Account account = repoAccount.findById(idAccount).orElse(null);
        Cart cart = account.getCart();
        List<CartDetails> cartDetailsList = cart.getCartDetails();
        for (CartDetails cartDetailsperline : cartDetailsList){
            repoCartDetails.deleteById(cartDetailsperline.getIdCartDetail());
        }
        repoCart.deleteById(cart.getIdCart());

        List<Receipt> receiptList = account.getReceiptList();
        for(Receipt receiptperline : receiptList){
            List<ReceiptDetails> receiptDetailsList = receiptperline.getReceiptDetailsList();
            for(ReceiptDetails receiptDetailsperline : receiptDetailsList){
                repoReceiptDetails.deleteById(receiptDetailsperline.getIdReceiptDetails());
            }
            repoReceipt.deleteById(receiptperline.getIdReceipt());
        }

        repoAccount.deleteById(idAccount);
    }

    @PutMapping(value = "/admin/changerole")
    public void ChangeRole(@RequestParam long idAccount , @RequestParam Role role){
        Account account = repoAccount.findById(idAccount).orElse(null);
        account.setIdRole(role);
        repoAccount.save(account);
    }

    @GetMapping(value = "/main/me")
    public Account ReturnUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByAccUsername(username);
        return account;
    }

}
