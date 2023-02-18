package controller;

import dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.userService;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService service;

    @PostMapping("/add")
    public void addUser(@RequestBody userDTO user){
        service.saveUser(user);
    }

}
