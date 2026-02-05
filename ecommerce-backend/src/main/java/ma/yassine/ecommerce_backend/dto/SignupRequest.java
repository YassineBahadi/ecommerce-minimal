package ma.yassine.ecommerce_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author pc
 **/
@Data
public class SignupRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min=6,max=40)
    private String password;

    @NotBlank
    @Size(min = 2,max = 50)
    private String firstName;

    @NotBlank
    @Size(min=2,max = 50)
    private String lastName;

}
