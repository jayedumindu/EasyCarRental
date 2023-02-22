package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class driverDTO {
    private String username;
    private String password;
    private String fName;
    private String lName;
    private BigDecimal revenue;
    private String contactNo;
    private byte[] profile;
    private String license;
}
