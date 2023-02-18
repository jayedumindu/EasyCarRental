package controller;

import dto.userDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repo.UserRepo;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserRepo repo;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/add")
    public String getCustomer(@RequestBody userDTO user){
        repo.save()
        return user.toString();
    }

}
