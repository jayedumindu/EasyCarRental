package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Booking {
    @Id
    private String booking_id;
    private LocalDateTime current_date_time;
    private LocalDateTime due_date_time;
    private BigDecimal advance_payment;
    private String pickup_loc;
    private String return_loc;
    @Lob
    private byte[] payment_confirmation;
    private boolean isAccepted;
}
