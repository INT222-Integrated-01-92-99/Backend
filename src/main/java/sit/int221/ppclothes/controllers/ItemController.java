package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import sit.int221.ppclothes.models.Prowithcolors;
import sit.int221.ppclothes.repositories.repoProwithcolos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ItemController {
    @Autowired
    private repoProwithcolos repoProwithcolos;

    @GetMapping("item")
    public List<Prowithcolors> item(){
        return repoProwithcolos.findAll();
    }
}
