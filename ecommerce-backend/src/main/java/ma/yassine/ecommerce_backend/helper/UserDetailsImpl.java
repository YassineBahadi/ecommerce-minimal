package ma.yassine.ecommerce_backend.helper;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author pc
 **/
public class UserDetailsImpl extends org.springframework.security.core.userdetails.User{
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public UserDetailsImpl(Long id, String email, String password,
                           String firstName, String lastName,
                           Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}
