package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import sit.int221.ppclothes.models.Receipt;
import sit.int221.ppclothes.repositories.repoReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ReceiptController {
    @Autowired
    private repoReceipt repoReceipt;

    @GetMapping("/receipt")
    public List<Receipt> Receipt(){
        return repoReceipt.findAll();
    }

    @GetMapping("/receipt/{idreceipt}")
    public Receipt ReceiptbyId(@PathVariable Long idreceipt){
        Receipt receipt = repoReceipt.findById(idreceipt).orElse(null);
        return receipt;
    }

}
