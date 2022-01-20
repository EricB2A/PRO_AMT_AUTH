package amt.auth.Model;

import amt.auth.Validation.PasswordConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
public class User extends ValidatedModel{
    static private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
    @NonNull
    private String role;

    public User(@NonNull String username, @NonNull String password, @NonNull String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        validateOrThrow();
        this.password = passwordEncoder.encode(password);
    }

    public boolean passwordMatch(CharSequence toComparePassword){
        return passwordEncoder.matches(toComparePassword, this.password);
    }
}
