package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Booking {
    @Id
    private String bookingId;
    private LocalDate currenDateTime;
    private LocalDate dueDateTime;
    private BigDecimal advancePayment;
    @Lob
    private byte[] paymentConfirmation;
    private boolean isAccepted;

    @ManyToOne
    @JsonBackReference
    private Car car;
    @ManyToOne
    @JsonBackReference
    private Driver driver;
    @ManyToOne
    @JsonBackReference
    private Admin admin;
    @ManyToOne
    @JsonBackReference
    private User user;
}
