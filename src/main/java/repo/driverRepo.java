package repo;

import entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface driverRepo extends JpaRepository<Driver,String> {
}
