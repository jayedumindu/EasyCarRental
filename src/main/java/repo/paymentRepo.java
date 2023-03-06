package repo;

import dto.paymentDTO;
import entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface paymentRepo extends JpaRepository<Payment,String> {
    @Modifying
    @Query(value = "UPDATE Payment p SET p.rent=:rent, p.account=:acc, p.deduction=:ded, p.payment_method=:pMethod, p.returnDateTime=:date WHERE p.bookingId=:id")
    void updatePayment(@Param("rent") BigDecimal rent,@Param("acc") String acc, @Param("ded") BigDecimal ded, @Param("pMethod") String pMethod, @Param("date") LocalDate date, @Param("id") String id);
    Payment getPaymentByBookingId(String bookingId) ;
}
