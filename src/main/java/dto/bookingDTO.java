package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Transient;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class bookingDTO {
    private String bookingId;
    private LocalDate currenDateTime;
    private LocalDate dueDateTime;
    private BigDecimal advancePayment;
    private byte[] paymentConfirmation;
    private boolean isAccepted;

    private String carId;

    private String driverId;

    private String userId;


    private carDTO car;
    private driverDTO driver;
    private adminDTO admin;
    private userDTO user;
    private ByteArrayInputStream conf;

    public bookingDTO(String id,LocalDate currenDateTime, LocalDate dueDateTime, BigDecimal advancePayment, byte[] paymentConfirmation, boolean isAccepted, carDTO car, driverDTO driver, userDTO user) {
        this.bookingId = id;
        this.currenDateTime = currenDateTime;
        this.dueDateTime = dueDateTime;
        this.advancePayment = advancePayment;
        this.paymentConfirmation = paymentConfirmation;
        this.isAccepted = isAccepted;
        this.car = car;
        this.driver = driver;
        this.user = user;

    }

    public bookingDTO(String bookingId, LocalDate currenDateTime, LocalDate dueDateTime, BigDecimal advancePayment, byte[] paymentConfirmation, boolean isAccepted, carDTO car, userDTO user) {
        this.bookingId = bookingId;
        this.currenDateTime = currenDateTime;
        this.dueDateTime = dueDateTime;
        this.advancePayment = advancePayment;
        this.paymentConfirmation = paymentConfirmation;
        this.isAccepted = isAccepted;
        this.car = car;
        this.user = user;
    }

    //    public bookingDTO(String bookingId, LocalDate currenDateTime, LocalDate dueDateTime, BigDecimal advancePayment, byte[] paymentConfirmation, boolean isAccepted, String carId, String driverId, String userId, carDTO car, driverDTO driver, adminDTO admin, userDTO user, Blob conf) throws SQLException {
//        this.bookingId = bookingId;
//        this.currenDateTime = currenDateTime;
//        this.dueDateTime = dueDateTime;
//        this.advancePayment = advancePayment;
//        this.paymentConfirmation = paymentConfirmation;
//        this.isAccepted = isAccepted;
//        this.carId = carId;
//        this.driverId = driverId;
//        this.userId = userId;
//        this.car = car;
//        this.driver = driver;
//        this.admin = admin;
//        this.user = user;
//
//
//    }
}
