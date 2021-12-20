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
public class CredentialDTO {
    @Getter
    @Setter
    @NotNull
    // DPE - Les annotations sont très utiles, mais on mélange une "logique" métier dans une DTO. Et ce n'est pas le but du pattern
    @Size(min = 5,message = "Le nom d'utilisateur doit être composé d'au moins 5 caractères")
    private String username;
    @Getter
    @Setter
    @NotNull
    @PasswordConstraint
    private String password;
}
