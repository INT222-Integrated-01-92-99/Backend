package sit.int221.ppclothes.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.ppclothes.models.Role;
import sit.int221.ppclothes.repositories.repoRole;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private repoRole repoRole;

    @GetMapping ("/admin/role")
    public List<Role> Role(){
        return repoRole.findAll();
    }

}
