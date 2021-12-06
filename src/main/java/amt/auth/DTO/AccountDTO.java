package amt.auth.DTO;

import amt.auth.Model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AccountDTO {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String role;
    public AccountDTO(User user){
        this(user.getId(), user.getUsername(), user.getRole());
    }
}
