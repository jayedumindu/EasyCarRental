package repo;

import entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface carRepo extends JpaRepository<Car,String> {
    Car findCarByRegistrationNumber(String regNo);
}
