package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
