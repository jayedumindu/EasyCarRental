package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties
    private Collection<Booking> bookings = new ArrayList<>();

    public Driver(String username) {
        this.username = username;
    }
}
