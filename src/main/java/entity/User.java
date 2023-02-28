package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    @Id
    private String username;
    private String pwd;
    private String name;
    private String address;
    private String contact;
    @Column(name = "NIC")
    private String nic;
    private String license;
    @Lob
    private byte[] nicVerification;
    @Lob
    private byte[] licenseVerification;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Collection<Booking> bookings = new ArrayList<>();

}
