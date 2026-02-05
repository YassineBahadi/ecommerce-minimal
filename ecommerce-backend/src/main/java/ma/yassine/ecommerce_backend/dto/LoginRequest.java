package ma.yassine.ecommerce_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author pc
 **/
@Data
public class LoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;


}
