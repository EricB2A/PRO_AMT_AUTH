package amt.auth.DTO;


import amt.auth.Validation.PasswordConstraint;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class CredentialDTO {
    private String username;
    private String password;
}
