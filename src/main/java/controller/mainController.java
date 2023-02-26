package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class mainController {

    public mainController() {
        System.out.println("main controller instantiated");
    }

    @GetMapping("index")
    public String getHomePage(){
        System.out.println("index page called");
        return "index";
    }

    @GetMapping("admin")
    public String getCustomer(){
        System.out.println("admin page called");
        return "admin";
    }


}
