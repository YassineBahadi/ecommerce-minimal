package ma.yassine.ecommerce_backend.dto;

import lombok.Data;

import java.util.List;

/**
 * @author pc
 **/
@Data
public class LoginResponse {
    private String token;
    private String type="Bearer";
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<String>roles;

    public LoginResponse(String token, Long id, String email,
                         String firstName, String lastName, List<String> roles) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }



}
