package controller;

import dto.carDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.carService;
import util.ResponseUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/car")
public class carController {

    @Autowired
    carService carService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseUtil saveCar(
                                        @RequestParam String registrationNumber,
                                        @RequestParam String brand,
                                        @RequestParam String model,
                                        @RequestParam String type,
                                        @RequestParam int noOfPassengers,
                                        @RequestParam int mileage,
                                        @RequestParam int serviceMileage,
                                        @RequestParam String transmissionType,
                                        @RequestParam String fuelType,
                                        @RequestParam BigDecimal dailyRate,
                                        @RequestParam BigDecimal monthlyRate,
                                        @RequestParam int freeMileageForMonth,
                                        @RequestParam int freeMileageForDay,
                                        @RequestParam BigDecimal priceForExtraKM,
                                        @RequestParam String color,
                                        @RequestParam boolean availability,
                                        @RequestPart("file1") MultipartFile file1,
                                        @RequestPart("file2") MultipartFile file2,
                                        @RequestPart("file3") MultipartFile file3,
                                        @RequestPart("file4") MultipartFile file4
                                        ) throws IOException {

        carDTO dto = new carDTO(registrationNumber,brand,model,type,noOfPassengers,mileage,serviceMileage,transmissionType,fuelType,dailyRate,monthlyRate,freeMileageForMonth,freeMileageForDay,priceForExtraKM,color,availability,file1.getBytes(),file2.getBytes(),file3.getBytes(),file4.getBytes());
        carService.saveCar(dto);
        return new ResponseUtil("OK","Successfully Registered.!",null);

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseUtil updateCar(
                                        @RequestParam String registrationNumber,
                                        @RequestParam String brand,
                                        @RequestParam String model,
                                        @RequestParam String type,
                                        @RequestParam int noOfPassengers,
                                        @RequestParam int mileage,
                                        @RequestParam int serviceMileage,
                                        @RequestParam String transmissionType,
                                        @RequestParam String fuelType,
                                        @RequestParam BigDecimal dailyRate,
                                        @RequestParam BigDecimal monthlyRate,
                                        @RequestParam int freeMileageForMonth,
                                        @RequestParam int freeMileageForDay,
                                        @RequestParam BigDecimal priceForExtraKM,
                                        @RequestParam String color,
                                        @RequestParam boolean availability,
                                        @RequestPart(name = "file1", required = false) MultipartFile file1,
                                        @RequestPart(name = "file2",required = false) MultipartFile file2,
                                        @RequestPart(name = "file3",required = false) MultipartFile file3,
                                        @RequestPart(name = "file4",required = false) MultipartFile file4
                                        ) throws IOException {

        carDTO dto = new carDTO(registrationNumber,brand,model,type,noOfPassengers,mileage,serviceMileage,transmissionType,fuelType,dailyRate,monthlyRate,freeMileageForMonth,freeMileageForDay,priceForExtraKM,color,availability,file1.getBytes(),file2.getBytes(),file3.getBytes(),file4.getBytes());
        carService.updateCar(dto);
        return new ResponseUtil("OK","Successfully Updated.!",null);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public ResponseUtil removeCar(@RequestParam String registrationNumber){
        carService.deleteCar(registrationNumber);
        return new ResponseUtil("OK","Successfully Deleted.!",null);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseUtil getAllCar(){
        ArrayList<carDTO> data = carService.getAllCar();
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseUtil getAllCar(@RequestParam String registrationNumber){
        carDTO data = carService.findCarByRegNo(registrationNumber);
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/isAvailable", method = RequestMethod.GET)
    public ResponseUtil checkAvailability(@RequestParam String regNo,@RequestParam String date1, @RequestParam String date2){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dt1 = LocalDate.parse(date1,dateTimeFormatter);
        LocalDate dt2 = LocalDate.parse(date2,dateTimeFormatter);
        boolean data = carService.isCarAvailable(regNo, dt1, dt2) == 0;
        return new ResponseUtil("OK","Successful!",data);
    }

    @RequestMapping(value = "/countCarsByAvailabilityIsTrue", method = RequestMethod.GET)
    public ResponseUtil countCarsByAvailabilityIsTrue(){
        return new ResponseUtil("OK","Successful!",carService.countCarsByAvailabilityIsTrue());
    }

    @RequestMapping(value = "/countCarsScheduled", method = RequestMethod.GET)
    public ResponseUtil countCarsScheduled(){
        return new ResponseUtil("OK","Successful!",carService.countCarsScheduled());
    }


}
