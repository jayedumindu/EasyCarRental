package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

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

}
