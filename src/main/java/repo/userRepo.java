package repo;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User, String> {
    User findUserByUsername(String username);
}
