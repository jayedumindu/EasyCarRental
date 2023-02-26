package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
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


    private carDTO car;
    private driverDTO driver;
    private adminDTO admin;
    private userDTO user;

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
}
