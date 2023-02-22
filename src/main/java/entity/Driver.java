package entity;

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
    private Collection<Booking> bookings = new ArrayList<>();


}
