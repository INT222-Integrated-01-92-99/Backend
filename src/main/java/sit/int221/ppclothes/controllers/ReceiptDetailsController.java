package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import sit.int221.ppclothes.models.ReceiptDetails;
import sit.int221.ppclothes.repositories.repoReceiptDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ReceiptDetailsController {
    @Autowired
    private repoReceiptDetails repoReceiptDetails;

    @GetMapping("/receiptdetails")
    public List<ReceiptDetails> RepoReceiptDetails(){
        return repoReceiptDetails.findAll();
    }
}
