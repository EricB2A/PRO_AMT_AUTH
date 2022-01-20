package amt.auth.Model;

import amt.auth.DTO.CredentialDTO;
import amt.auth.Validation.PasswordConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class User extends ValidatedModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NonNull
    @Size(min=5, max=50)
    private String username;
    @Column
    @NonNull
    @Setter
    @PasswordConstraint
    private String password;

    @Column
    @NotBlank
    @Setter
    private String role;

    public User(String username, String password, String role){
        validateOrThrow();
    }
}
