package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class carDTO {
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
    private byte[] img_front;
    private byte[] img_back;
    private byte[] img_side;
    private byte[] img_interior;
}
