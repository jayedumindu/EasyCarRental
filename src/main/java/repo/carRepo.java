package repo;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface carRepo extends JpaRepository<Car,String> {
    Car findCarByRegistrationNumber(String regNo);
//    available cars
    int countCarsByAvailabilityIsTrue();
//    cars scheduled
@Query(value = "select COUNT(*) from Car INNER JOIN Booking ON Car.registrationNumber = Booking.car_registrationNumber ",nativeQuery = true)
    int countCarsScheduled();
}
