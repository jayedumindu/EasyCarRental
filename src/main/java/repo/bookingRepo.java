package repo;

import entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface bookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "select bookingId from Booking ORDER BY bookingId DESC LIMIT 1;",nativeQuery = true)
    String getLastBookingId();
    @Query(value = "select * from Booking where CURDATE() between currenDateTime and dueDateTime;",nativeQuery = true)
    Collection<Booking> getBookingsByToday();
}
