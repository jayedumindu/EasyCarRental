package repo;

import entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface adminRepo extends JpaRepository<Admin,String> {
    Admin findAdminByUsername(String username);
}
