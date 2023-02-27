package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Payment {

    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "booking_id",referencedColumnName="bookingId")
    private Booking booking;

    private BigDecimal rent;
    private String account;
    private String payment_method;
    private BigDecimal deduction;
    private LocalDate returnDateTime;

}
