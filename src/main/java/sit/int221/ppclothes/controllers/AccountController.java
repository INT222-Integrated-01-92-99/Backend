package sit.int221.ppclothes.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.AccountException;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.repoAccount;
import sit.int221.ppclothes.repositories.repoCart;
import sit.int221.ppclothes.repositories.repoCartDetails;
import sit.int221.ppclothes.repositories.repoBrand;
import sit.int221.ppclothes.repositories.repoReceipt;
import sit.int221.ppclothes.repositories.repoReceiptDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoCart repoCart;
    @Autowired
    private repoBrand repoBrand;
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
        Cart newCart = new Cart(0,0);
        repoCart.save(newCart);
        String encodedpassword = passwordEncoder.encode(newAccount.getAccPass());
        Account newAccountnoReceipt = new Account(0,newAccount.getAccUsername(),encodedpassword,newAccount.getAccFname(), newAccount.getAccLname(), newAccount.getAccPhone(), newAccount.getAccAddress(),newAccount.getIdRole(),newCart);
        repoAccount.save(newAccountnoReceipt);
        return newAccount;
    }

    @PutMapping(value = "/allroles/editaccount")
    public Account EditAccount(@RequestBody Account editAccount){
        return repoAccount.save(editAccount);
    }


    @DeleteMapping(value = "/admin/deleteaccount")
    public void DelAccount(@RequestParam long idAccount){
        Account account = repoAccount.findById(idAccount).orElse(null);
        Cart cart = account.getCart();
        if( cart.getCartDetails() != null ){
            List<CartDetails> cartDetailsList = cart.getCartDetails();
            for (CartDetails cartDetailsperline : cartDetailsList){
                repoCartDetails.deleteById(cartDetailsperline.getIdCartDetail());
            }
        }
        if( account.getReceiptList() != null ){
            List<Receipt> receiptList = account.getReceiptList();
            for(Receipt receiptperline : receiptList){
                List<ReceiptDetails> receiptDetailsList = receiptperline.getReceiptDetailsList();
                for(ReceiptDetails receiptDetailsperline : receiptDetailsList){
                    repoReceiptDetails.deleteById(receiptDetailsperline.getIdReceiptDetails());
                }
                repoReceipt.deleteById(receiptperline.getIdReceipt());
            }
        }
        repoAccount.deleteById(idAccount);
        repoCart.deleteById(account.getCart().getIdCart());
    }

    @PutMapping(value = "/admin/changerole")
    public void ChangeRole(@RequestParam long idAccount , @RequestParam Role role){
        Account account = repoAccount.findById(idAccount).orElse(null);
        account.setIdRole(role);
        repoAccount.save(account);
    }

    @GetMapping(value = "/allroles/me")
    public Account ReturnUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        Account account = repoAccount.findByAccUsername(username);
        return account;
    }

    @GetMapping(value = "/staff/brand")
    public List<Brand> PresentStaffGetBrand(){
        return repoBrand.findAll();
    }

    @GetMapping(value = "/staff/account")
    public List<Account> PresentStaffGetAccount(){
        return repoAccount.findAll();
    }

    @GetMapping(value = "/member/brand")
    public List<Brand> PresentMemberGetBrand(){
        return repoBrand.findAll();
    }

}