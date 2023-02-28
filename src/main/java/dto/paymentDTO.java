package dto;

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
    private bookingDTO booking;
    private BigDecimal rent;
    private String account;
    private String payment_method;
    private BigDecimal deduction;
    private LocalDate returnDateTime;

    public paymentDTO(bookingDTO booking, BigDecimal rent) {
        this.booking = booking;
        this.rent = rent;
    }

    public paymentDTO(String bookingId, bookingDTO booking, BigDecimal rent) {
        this.bookingId = bookingId;
        this.booking = booking;
        this.rent = rent;
    }

    public paymentDTO(BigDecimal rent, String account, String payment_method, BigDecimal deduction, LocalDate returnDateTime) {
        this.rent = rent;
        this.account = account;
        this.payment_method = payment_method;
        this.deduction = deduction;
        this.returnDateTime = returnDateTime;
    }

}
