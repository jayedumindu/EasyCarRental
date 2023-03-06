package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    @JsonIgnoreProperties
    private Collection<Booking> bookings = new ArrayList<>();

}
