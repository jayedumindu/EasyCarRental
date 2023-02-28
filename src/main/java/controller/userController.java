package controller;

import dto.adminDTO;
import dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.userService;
import util.ResponseUtil;

import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class userController {

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
        userService.saveUser(dto);
        return new ResponseUtil("OK","Successfully Registered.!",null);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "multipart/form-data")
    public ResponseUtil updateUser( @RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String address,
                                 @RequestParam String license,
                                 @RequestParam String nic,
                                 @RequestParam String contact,
                                 @RequestParam String pwd,
                                 @RequestPart(name ="file1") MultipartFile file1,
                                 @RequestPart("file2") MultipartFile file2) throws IOException {
        userDTO dto = new userDTO(username,pwd,name,address,contact,nic,license,file1.getBytes(),file2.getBytes());
        userService.updateUser(dto);
        return new ResponseUtil("OK","Successfully Updated.!",null);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ResponseUtil removeUser(@RequestParam String username){
        userService.deleteUser(username);
        return new ResponseUtil("OK","Successfully Deleted.!",null);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseUtil getUser(@RequestParam String username){
        userDTO dto = userService.findUserByUsername(username);
        return new ResponseUtil("OK","Successful!",dto);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseUtil getAllUser(){
        ArrayList<userDTO> data = userService.getAllUser();
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseUtil validateAdmin(@RequestParam String pwd,@RequestParam String username){
        adminDTO data = userService.validateAdmin(username);
        if(data!=null){
            if(data.getPassword().equals(pwd)){
                return new ResponseUtil("OK","Successful!",1);
            }else {
                return new ResponseUtil("OK","Successful!",0);
            }
        }else {
            return new ResponseUtil("OK","Successful!",0);
        }

    }

}
