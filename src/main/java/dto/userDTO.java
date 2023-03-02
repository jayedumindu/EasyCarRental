package dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class userDTO {
    private String username;
    private String pwd;
    private String name;
    private String address;
    private String contact;
    private String nic;
    private String license;
    private byte[] nicVerification;
    private byte[] licenseVerification;
    @JsonIgnore
    private Collection<bookingDTO> bookings;

    public userDTO(String username, String name, String address, String contact, String nic, String license) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
        this.license = license;
    }

    public userDTO(String username, String pwd, String name, String address, String contact, String nic, String license, byte[] nicVerification, byte[] licenseVerification) {
        this.username = username;
        this.pwd = pwd;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.nic = nic;
        this.license = license;
        this.nicVerification = nicVerification;
        this.licenseVerification = licenseVerification;
    }
}
