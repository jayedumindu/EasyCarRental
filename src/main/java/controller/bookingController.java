package controller;

import dto.bookingDTO;
import dto.driverDTO;
import dto.paymentDTO;
import dto.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.bookingService;
import service.carService;
import service.driverService;
import service.userService;
import util.ResponseUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class bookingController {
    @Autowired
    bookingService bkService;

    @Autowired
    carService crService;

    @Autowired
    userService uService;

    @Autowired
    driverService drService;

    @RequestMapping(value = "/place", method = RequestMethod.POST)
    public ResponseUtil placeBooking(
            @RequestParam BigDecimal advancePayment,
                                  @RequestParam String bookingId,
                                  @RequestParam  String currentDateTime,
                                  @RequestParam String dueDateTime,
                                  @RequestParam boolean isAccepted,
                                  @RequestPart("paymentConfirmation") byte[] conf,
                                  @RequestParam String user,
                                  @RequestParam(required = false) String driver,
                                  @RequestParam String car,
                                  @RequestParam BigDecimal rent
                                  ) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate crd = LocalDate.parse(currentDateTime,dateTimeFormatter);
        LocalDate dt = LocalDate.parse(dueDateTime,dateTimeFormatter);
//        System.out.println(driver);
//        System.out.println("start");
        System.out.println(driver.getClass().getSimpleName());
        bookingDTO dto1;
        if(driver.equals("null")){
            System.out.println("null");
            dto1 = new bookingDTO(bookingId,crd,dt,advancePayment,conf,isAccepted,crService.findCarByRegNo(car),uService.findUserByUsername(user));
        }else  {
            System.out.println("not null");
            dto1 = new bookingDTO(bookingId,crd,dt,advancePayment,conf,isAccepted,crService.findCarByRegNo(car),drService.findDriverByUsername(driver), uService.findUserByUsername(user));}

//        bookingDTO dto1 = new bookingDTO(bookingId,crd,dt,advancePayment,conf,isAccepted,crService.findCarByRegNo(car),drService.findDriverByUsername(driver), uService.findUserByUsername(user));
        paymentDTO dto2 = new paymentDTO(dto1.getBookingId(),dto1,rent,false);
        bkService.placeBooking(dto1,dto2);
        return new ResponseUtil("OK","Successfully Added.!",null);
    }

    @RequestMapping(value = "/lastId", method = RequestMethod.GET)
    public ResponseUtil getLastBookingId(){
        return new ResponseUtil("OK","Successful!", bkService.getLastBookingId());
    }

    @RequestMapping(value = "/getBookingActiveToday", method = RequestMethod.GET)
    public ResponseUtil getBookingActiveToday(){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getBookingActiveToday());
    }

    @RequestMapping(value = "/getBookingsForToday", method = RequestMethod.GET)
    public ResponseUtil getBookingsForToday(){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getBookingsForToday());
    }

    @RequestMapping(value = "/getBookingsByAcceptedFalse", method = RequestMethod.GET)
    public ResponseUtil getBookingsByAcceptedFalse(){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getBookingsByAcceptedFalse());
    }

    @RequestMapping(value = "/getBooking", method = RequestMethod.GET)
    public ResponseUtil getBookingById(@RequestParam String bId){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getBookingById(bId));
    }

    @RequestMapping(value = "/getPayment", method = RequestMethod.GET)
    public ResponseUtil getPaymentById(@RequestParam String pId){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getPaymentById(pId));
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil getPendingBookings(@RequestParam String username){
        userDTO dto = uService.findUserByUsername(username);
        return new ResponseUtil("OK","Successful!", dto.getBookings());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseUtil deleteBooking(@RequestParam String id){
        bkService.removeBooking(id);
        return new ResponseUtil("OK","Successful!", null);
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil acceptBooking(@RequestParam String id){
        bkService.acceptBooking(id);
        return new ResponseUtil("OK","Successful!", null);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil findBookingsForUser(@RequestParam String id){
        return new ResponseUtil("OK","Successful!", bkService.findBookingsForUser(id));
    }

    @RequestMapping(value = "/paymentExist", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil checkIfPaymentExists(@RequestParam String pId){
        return new ResponseUtil("OK","Successful!", bkService.checkIfPaymentExist(pId));
    }

    @RequestMapping(value = "/calDailyRev", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil calculateDailyRevenue(){
        return new ResponseUtil("OK","Successful!", null);
    }

    @RequestMapping(value = "/calMonthlyRev", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil calculateMonthlyRevenue(){
        return new ResponseUtil("OK","Successful!", null);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil doPayment(
            @RequestParam BigDecimal rent,
            @RequestParam String account,
            @RequestParam BigDecimal deduction,
            @RequestParam String method,
            @RequestParam String date,
            @RequestParam String id,
            @RequestParam boolean done
                                  ){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate returnDate = LocalDate.parse(date,dateTimeFormatter);
        paymentDTO dto = new paymentDTO(rent,account,method,deduction,returnDate,done);
        System.out.println(date);
        bkService.upDatePayment(dto,id);
        return new ResponseUtil("OK","Successful!", null);
    }

}
