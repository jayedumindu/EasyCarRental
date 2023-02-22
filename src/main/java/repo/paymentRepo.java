package repo;

import entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface paymentRepo extends JpaRepository<Payment,String> {
}
