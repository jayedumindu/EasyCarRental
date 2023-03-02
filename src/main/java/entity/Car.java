package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties
    @JsonIgnore
    private Collection<Booking> bookings = new ArrayList<>();

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
