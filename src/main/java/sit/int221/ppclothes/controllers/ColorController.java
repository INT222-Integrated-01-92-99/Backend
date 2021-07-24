package sit.int221.ppclothes.controllers;

import sit.int221.ppclothes.models.Color;
import sit.int221.ppclothes.repositories.repoColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColorController {
    @Autowired
    private repoColor repoColor;

    @GetMapping("/color")
    public List<Color> colors(){
        return repoColor.findAll();
    }
}