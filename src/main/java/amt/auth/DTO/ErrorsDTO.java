package amt.auth.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorsDTO {
    private List<ErrorDTO> errors;
    public ErrorsDTO(){
        errors = new ArrayList<>();
    }
    public void add(ErrorDTO error){
        errors.add(error);
    }
}
