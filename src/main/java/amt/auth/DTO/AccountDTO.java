package amt.auth.DTO;

import amt.auth.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private Integer id;
    private String username;
    private String role;
    public AccountDTO(User user){
        this(user.getId(), user.getUsername(), user.getRole());
    }
}
