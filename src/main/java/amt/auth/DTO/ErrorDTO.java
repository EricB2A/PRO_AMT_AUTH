package amt.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorDTO {
    @Getter
    @Setter
    private String property;
    @Getter
    @Setter
    private String message;
}
