package sit.int221.ppclothes.controllers;

import sit.int221.ppclothes.models.Account;
import sit.int221.ppclothes.repositories.repoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private repoAccount repoAccount;

    @GetMapping("/account")
    public List<Account> Account(){
        return repoAccount.findAll();
    }
}
