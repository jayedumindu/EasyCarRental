package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@SqlResultSetMapping(
        name="groupDetailsMapping",
        classes={
                @ConstructorResult(
                        targetClass=Booking.class,
                        columns={
                                @ColumnResult(name = "bookingId", type = String.class),
                                @ColumnResult(name = "user_username", type = String.class),
                                @ColumnResult(name = "car_registrationNumber", type = String.class),
                                @ColumnResult(name = "driver_username", type = String.class),
                                @ColumnResult(name = "paymentConfirmation", type = Blob.class),

                        }
                )
        }
)

@NamedNativeQuery(name="Booking.getGroupDetails", query="select b.bookingId,b.user_username,b.car_registrationNumber,b.driver_username,b.paymentConfirmation from Booking b where isAccepted = false", resultSetMapping="groupDetailsMapping")
public class Booking {
    @Id
    private String bookingId;
    private LocalDate currenDateTime;
    private LocalDate dueDateTime;
    private BigDecimal advancePayment;
    @Lob
    private byte[] paymentConfirmation;
    private boolean isAccepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Car car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Admin admin;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @Transient
    private String carId;
    @Transient
    private String driverId;
    @Transient
    private String userId;
    @Transient
    private Blob conf;


    public Booking(String bookingId, String car, String driver, String user, Blob arr) {
        this.bookingId = bookingId;
        this.carId = car;
        this.driverId = driver;
        this.userId = user;
        this.conf = arr;
    }

}
