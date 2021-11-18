package sit.int221.ppclothes.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.repoAccount;
import sit.int221.ppclothes.repositories.repoCart;
import sit.int221.ppclothes.repositories.repoRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoCart repoCart;
    @Autowired
    private repoRole repoRole;

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
        long newIDcart = repoCart.getMaxCartId() + 1;
        long newIDaccount = repoAccount.getMaxidAcc() + 1;
        Account newAccountnoReceipt = new Account(newIDaccount,newAccount.getAccUsername(),newAccount.getAccPass(),newAccount.getAccFname(), newAccount.getAccLname(), newAccount.getAccPhone(), newAccount.getAccAddress(),newAccount.getIdRole());
        Cart newCart = new Cart(newIDcart,0);
        repoCart.save(newCart);
        newAccountnoReceipt.setCart(newCart);
        repoAccount.save(newAccountnoReceipt);
        return newAccount;
    }

    @PutMapping(value = "/allroles/editaccount")
    public Account EditAccount(@RequestBody Account editAccount){
        return repoAccount.save(editAccount);
    }


    @DeleteMapping(value = "/admin/deleteaccount")
    public void DelAccount(@RequestParam long idAccount){
        repoAccount.deleteById(idAccount);
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

}
