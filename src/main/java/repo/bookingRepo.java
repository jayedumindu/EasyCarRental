package repo;

import entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

public interface bookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "select bookingId from Booking ORDER BY bookingId DESC LIMIT 1;",nativeQuery = true)
    String getLastBookingId();
    @Query(value = "select COUNT(*) from Booking where CURDATE() between currenDateTime and dueDateTime;",nativeQuery = true)
    int getBookingActiveToday();
    @Query(value = "select COUNT(*) from Booking where CURDATE() = currenDateTime;",nativeQuery = true)
    int getBookingsForToday();
@Query(value = "select * from Booking where isAccepted = false", nativeQuery = true)
    Collection<Booking> getBookingsByAcceptedFalse();

}
