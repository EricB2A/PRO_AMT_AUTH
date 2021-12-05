package amt.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDTO {
    @Getter
    @Setter
    private List<ErrorDTO> errors;
    public ErrorsDTO(){
        errors = new ArrayList<>();
    }
    public void add(ErrorDTO error){
        errors.add(error);
    }
}
