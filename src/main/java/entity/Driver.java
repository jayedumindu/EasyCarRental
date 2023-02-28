package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Driver {
    @Id
    private String username;
    private String password;
    private String fName;
    private String lName;
    private String contactNo;
    @Lob
    private byte[] profile;
    private String license;

    @OneToMany(mappedBy = "driver")
    @JsonManagedReference
    private Collection<Booking> bookings = new ArrayList<>();

    public Driver(String username) {
        this.username = username;
    }
}
