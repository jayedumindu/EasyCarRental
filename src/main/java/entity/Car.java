package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Car {
    @Id
    private String registrationNumber;
    private String brand;
    private String model;
    private String type;
    private int noOfPassengers;
    private int mileage;
    private int serviceMileage;
    private String transmissionType;
    private String fuelType;
    private BigDecimal dailyRate;
    private BigDecimal monthlyRate;
    private int freeMileageForMonth;
    private int freeMileageForDay;
    private BigDecimal priceForExtraKM;
    private String color;
    private boolean availability;
    @Lob
    private byte[] img_front;
    @Lob
    private byte[] img_back;
    @Lob
    private byte[] img_side;
    @Lob
    private byte[] img_interior;

    @OneToMany(mappedBy = "car")
    private Collection<Booking> bookings = new ArrayList<>();
}