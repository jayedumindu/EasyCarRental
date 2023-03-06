package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Admin {
    @Id
    private String username;
    private String password;
    private String name;
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties
    @JsonIgnore
    private Collection<Booking> bookings = new ArrayList<>();

}
