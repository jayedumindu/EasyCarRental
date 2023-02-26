package repo;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface carRepo extends JpaRepository<Car,String> {
    Car findCarByRegistrationNumber(String regNo);
//    available cars
    Collection<Car> countCarsByAvailabilityIsTrue()
//    cars scheduled
@Query(value = "select * from Booking where CURDATE() between currenDateTime and dueDateTime;",nativeQuery = true)
    Collection<Car> countCarsScheduled()
}
