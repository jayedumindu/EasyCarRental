package repo;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface userRepo extends JpaRepository<User, String> {
    User findUserByUsername(String username);
    @Query(value = "select COUNT(*) from User", nativeQuery = true)
    int countAll();
    @Modifying
    @Query(value = "UPDATE User p SET p.address=:add,p.contact=:con,p.license=:lic,p.name=:nm,p.nic=:nc  WHERE p.username=:id",nativeQuery = true)
    void updateUser(@Param("id") String username,@Param("add") String address,@Param("con") String contact,@Param("lic") String license,@Param("nm") String name,@Param("nc") String nic);
}
