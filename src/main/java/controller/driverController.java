package controller;

import dto.driverDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.driverService;
import util.ResponseUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/driver")
public class driverController {

    @Autowired
    driverService driverService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseUtil addDriver(
                                @RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String fName,
                                @RequestParam String lName,
                                @RequestParam String license,
                                @RequestParam String contactNo,
                                @RequestPart(name ="file1") MultipartFile file1
                                ) throws IOException {
        driverDTO dto = new driverDTO(username,password,fName,lName,contactNo,file1.getBytes(), license);
        driverService.saveDriver(dto);
        return new ResponseUtil("OK","Successfully Registered.!",null);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseUtil updateDriver( @RequestParam String username,
                                    @RequestParam String password,
                                    @RequestParam String fName,
                                    @RequestParam String lName,
                                      @RequestParam String contactNo,
                                    @RequestParam String license,
                                    @RequestPart(name ="file1") MultipartFile file1) throws IOException {
        driverDTO dto = new driverDTO(username,password,fName,lName, contactNo, file1.getBytes(), license);
        driverService.updateDriver(dto);
        return new ResponseUtil("OK","Successfully Updated.!",null);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ResponseUtil removeDriver(@RequestParam String username){
        driverService.deleteDriver(username);
        return new ResponseUtil("OK","Successfully Deleted.!",null);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseUtil getAllUser(){
        ArrayList<driverDTO> data = driverService.getAllDriver();
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseUtil getDriverById(@RequestParam String username){
        driverDTO data = driverService.findDriverByUsername(username);
        return new ResponseUtil("OK","Successful!",data);

    }

    @RequestMapping(value = "/findRandom", method = RequestMethod.GET)
    public ResponseUtil getDriverRandomly(@RequestParam String date1, @RequestParam String date2){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dt1 = LocalDate.parse(date1,dateTimeFormatter);
        LocalDate dt2 = LocalDate.parse(date2,dateTimeFormatter);
        driverDTO data = driverService.findDriverRandomly(dt1,dt2);
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/getNoOfOccupiedDrivers", method = RequestMethod.GET)
    public ResponseUtil getNoOfOccupiedDrivers(){
        int data = driverService.getNoOfOccupiedDrivers();
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/getNoOfAvailableDrivers", method = RequestMethod.GET)
    public ResponseUtil getNoOfAvailableDrivers(){
        int data = driverService.getNoOfAvailableDrivers();

        return new ResponseUtil("OK","Successful!",data);
    }

}
