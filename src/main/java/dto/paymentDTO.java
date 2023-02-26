package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class paymentDTO {
    private bookingDTO booking;
    private BigDecimal rent;
    private String account;
    private String payment_method;
    private BigDecimal deduction;
    private LocalDateTime returnDateTime;

    public paymentDTO(bookingDTO booking, BigDecimal rent) {
        this.booking = booking;
        this.rent = rent;
    }
}
