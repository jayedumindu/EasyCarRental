package controller;

import dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.userService;
import util.ResponseUtil;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {

//    @RequestMapping(value = "/add", method = RequestMethod.POST,
//            consumes = {"multipart/form-data"})
//    public ResponseUtil addUser( @RequestPart("userInfo") userDTO user, @RequestPart("file1") MultipartFile file1, @RequestPart("file2") MultipartFile file2){
//        System.out.println("customer save called");
//        System.out.println(user);
//        System.out.println(file1);
//        System.out.println(file2);
//        return new ResponseUtil("OK","Successfully Registered.!",null);
//    }

    @Autowired
    userService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseUtil addUser( @RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String address,
                                 @RequestParam String license,
                                 @RequestParam String nic,
                                 @RequestParam String contact,
                                 @RequestParam String pwd,
                                 @RequestPart(name ="file1") MultipartFile file1,
                                 @RequestPart("file2") MultipartFile file2) throws IOException {
        userDTO dto = new userDTO(username,pwd,name,address,contact,nic,license,file1.getBytes(),file2.getBytes());
        System.out.println(dto);
        userService.saveUser(dto);
        return new ResponseUtil("OK","Successfully Registered.!",null);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseUtil uploadFile(){
        return new ResponseUtil("OK","Successfully Registered.!",null);
    }

}
