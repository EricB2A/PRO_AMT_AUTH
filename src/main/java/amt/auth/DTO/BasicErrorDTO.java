package amt.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class BasicErrorDTO {
    @Getter
    @Setter
    private String error;
}
