package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

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
    private String transmissionType;
    private String fuelType;
    private BigDecimal pricesForTheRentDurations;
    private int freeMileageForThePriceAndDuration;
    private BigDecimal priceForExtraKM;
    private String color;

}
