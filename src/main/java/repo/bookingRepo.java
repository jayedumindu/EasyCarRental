package repo;

import entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;


public interface bookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "select bookingId from Booking ORDER BY bookingId DESC LIMIT 1;",nativeQuery = true)
    String getLastBookingId();
    @Query(value = "select COUNT(*) from Booking where CURDATE() between currenDateTime and dueDateTime;",nativeQuery = true)
    int getBookingActiveToday();
    @Query(value = "select COUNT(*) from Booking where CURDATE() = currenDateTime;",nativeQuery = true)
    int getBookingsForToday();

//@Query(value = "select b.bookingId,b.user_username,b.car_registrationNumber,b.driver_username,b.admin_username from Booking b where isAccepted = false", nativeQuery = true)
    Collection<Booking> getGroupDetails();

    @Query(value = "UPDATE Booking p SET p.isAccepted=true  WHERE p.bookingId=:id",nativeQuery = true)
    void acceptBooking(@Param("id") String id);
}
