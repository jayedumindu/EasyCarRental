package repo;

import entity.Car;
import entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;

public interface carRepo extends JpaRepository<Car,String> {
    Car findCarByRegistrationNumber(String regNo);
//    available cars
    int countCarsByAvailabilityIsTrue();
//    cars scheduled
@Query(value = "select COUNT(*) from Car INNER JOIN Booking ON Car.registrationNumber = Booking.car_registrationNumber ",nativeQuery = true)
    int countCarsScheduled();
//AND :regNo NOT IN (select Booking.car_registrationNumber from Booking) OR :regNo NOT IN (select Booking.car_registrationNumber from Booking where (:date1 between Booking.currenDateTime and Booking.dueDateTime) and (:date2 between Booking.currenDateTime and Booking.dueDateTime))
    @Query(value = "select COUNT(*) FROM Booking b WHERE b.car_registrationNumber=:regNo AND (:date1 between b.currenDateTime and b.dueDateTime) AND (:date2 between b.currenDateTime and b.dueDateTime)  ",nativeQuery = true)
    int isCarAvailable(@Param("regNo") String regNo,@Param("date1") LocalDate date1,@Param("date2") LocalDate date2 );
}
