package repo;

import entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface driverRepo extends JpaRepository<Driver,String> {
    Driver findDriverByUsername(String username);
    @Query(value = "select * from Driver ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Driver selectDriverRandomly();
}
