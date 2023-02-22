package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mainController {

    public mainController() {
        System.out.println("controller instantiated");
    }

    @GetMapping("/index")
    public String returnIndex(){
//        System.out.println("index returned");
//        ModelAndView view = new ModelAndView();
//        view.setViewName("index.jsp");
        return "index.html";
    }

    @ResponseBody
    @GetMapping("/customer")
    public String getCustomer(){
        return "customer";
    }

//    @ResponseBody
//    @GetMapping("/index")
//    public String getIndex(){
//        return "index";
//    }

}
