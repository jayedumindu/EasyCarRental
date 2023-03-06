package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "booking_id",referencedColumnName="bookingId")

    private Booking booking;

    private BigDecimal rent;
    private String account;
    private String payment_method;
    private BigDecimal deduction;
    private LocalDate returnDateTime;
    private boolean done;

    public Payment(Booking booking, BigDecimal rent, String account, String payment_method, BigDecimal deduction, LocalDate returnDateTime) {
        this.booking = booking;
        this.rent = rent;
        this.account = account;
        this.payment_method = payment_method;
        this.deduction = deduction;
        this.returnDateTime = returnDateTime;
    }

    public Payment(Booking booking, BigDecimal rent) {
        this.booking = booking;
        this.rent = rent;
    }
}
