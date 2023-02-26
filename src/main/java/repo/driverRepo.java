package repo;

import entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collection;

public interface driverRepo extends JpaRepository<Driver,String> {
    Driver findDriverByUsername(String username);
//    @Query(value = "select * from Driver LEFT JOIN Booking ON Driver.username = Booking.driver_username WHERE Booking.driver_username=NULL ORDER BY RAND() LIMIT 1",nativeQuery = true)
    @Query(value = "select * from Driver WHERE Driver.username NOT IN (select Booking.driver_username from Booking) OR Driver.username NOT IN (select Booking.driver_username from Booking where CURDATE() between Booking.currenDateTime and Booking.dueDateTime) ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Driver selectDriverRandomly();
    @Query(value = "select COUNT(*) from Driver INNER JOIN Booking ON Driver.username = Booking.driver_username WHERE CURDATE() between Booking.currenDateTime and Booking.dueDateTime",nativeQuery = true)
    int getNoOfOccupiedDrivers();
    @Query(value = "select COUNT(*) from Driver INNER JOIN Booking ON Driver.username = Booking.driver_username WHERE CURDATE() NOT between Booking.currenDateTime and Booking.dueDateTime",nativeQuery = true)
    int getNoOfAvailableDrivers();
}
