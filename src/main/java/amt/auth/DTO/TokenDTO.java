package amt.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor

public class TokenDTO {

    @Getter
    @Setter
    private String token;


    @Getter
    @Setter
    private AccountDTO account;
}
