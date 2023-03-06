package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class paymentDTO {
    private String bookingId;
    private BigDecimal rent;
    private bookingDTO booking;
    private String account;
    private String payment_method;
    private BigDecimal deduction;
    private LocalDate returnDateTime;
    private boolean done;


    public paymentDTO(bookingDTO booking, BigDecimal rent) {
        this.booking = booking;
        this.rent = rent;
    }

    public paymentDTO(String bookingId, bookingDTO booking, BigDecimal rent) {
        this.bookingId = bookingId;
        this.booking = booking;
        this.rent = rent;
    }

    public paymentDTO(BigDecimal rent, String account, String payment_method, BigDecimal deduction, LocalDate returnDateTime, boolean done) {
        this.rent = rent;
        this.account = account;
        this.payment_method = payment_method;
        this.deduction = deduction;
        this.returnDateTime = returnDateTime;
        this.done = done;
    }

//    public paymentDTO(String bookingId, bookingDTO dto1, BigDecimal rent, boolean b) {
//    }


    public paymentDTO(String bookingId,  bookingDTO booking, BigDecimal rent, boolean done) {
        this.bookingId = bookingId;
        this.rent = rent;
        this.booking = booking;
        this.done = done;
    }
}
