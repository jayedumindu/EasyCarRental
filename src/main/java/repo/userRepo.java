package repo;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface userRepo extends JpaRepository<User, String> {
    User findUserByUsername(String username);
    @Query(value = "select COUNT(*) from User", nativeQuery = true)
    int countAll();
}
