package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.exceptions.ExceptionRepo;
import sit.int221.ppclothes.exceptions.ProductException;
import sit.int221.ppclothes.models.*;
import sit.int221.ppclothes.repositories.repoAccount;
import sit.int221.ppclothes.repositories.repoCart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;
    @Autowired
    private repoCart repoCart;

    @GetMapping("/account")
    public List<Account> Account(){
        return repoAccount.findAll();
    }

//    @PostMapping(value = "/regisaccount")
//    public Account register(@RequestBody Account newAccount){
//        return newAccount;
//    }

    @GetMapping("/account/{idacc}")
    public Account AccountById(@PathVariable Long idacc){
        Account account = repoAccount.findById(idacc).orElse(null);
        return account;
    }

}
