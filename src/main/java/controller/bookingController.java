package controller;

import dto.bookingDTO;
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
import java.util.Collection;

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
                                  @RequestParam String driver,
                                  @RequestParam String car,
                                  @RequestParam BigDecimal rent
                                  ) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate crd = LocalDate.parse(currentDateTime,dateTimeFormatter);
        LocalDate dt = LocalDate.parse(dueDateTime,dateTimeFormatter);
        System.out.println("start");
        bookingDTO dto1 = new bookingDTO(bookingId,crd,dt,advancePayment,conf,isAccepted,crService.findCarByRegNo(car),drService.findDriverByUsername(driver), uService.findUserByUsername(user));
        paymentDTO dto2 = new paymentDTO(dto1,rent);
//        System.out.println(dto1);
        bkService.placeBooking(dto1,dto2);
        return new ResponseUtil("OK","Successfully Added.!",null);
    }

    @RequestMapping(value = "/lastId", method = RequestMethod.GET)
    public ResponseUtil getLastBookingId(){
        System.out.println("method called");
        return new ResponseUtil("OK","Successful!", bkService.getLastBookingId());
    }

    @RequestMapping(value = "/pending", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtil getPendingBookings(@RequestParam String username){
        userDTO dto = uService.findUserByUsername(username);
//        Collection<bookingDTO> data = dto.getBookings();
        return new ResponseUtil("OK","Successful!", dto);
    }

}
