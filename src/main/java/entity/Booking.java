package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Booking {
    @Id
    private String bookingId;
    private LocalDateTime currenDateTime;
    private LocalDateTime dueDateTime;
    private BigDecimal advancePayment;
    private String pickupLocation;
    private String returnLocation;
    @Lob
    private byte[] paymentConfirmation;
    private boolean isAccepted;

    @ManyToOne
    private Car car;
    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Admin admin;

    @ManyToOne
    private User user;

}
