package repo;

import entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookingRepo extends JpaRepository<Booking,String> {
}
