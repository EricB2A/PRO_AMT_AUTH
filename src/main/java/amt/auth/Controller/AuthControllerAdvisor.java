package amt.auth.Controller;

import amt.auth.DTO.BasicErrorDTO;
import amt.auth.DTO.ErrorDTO;
import amt.auth.DTO.ErrorsDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;


/**
 * Cette classe permet de gérer les exceptions. Elle permet notamment de pouvoir formatter la réponse avec l'erreur comme souhaité.
 */
@ControllerAdvice
@RestController
public class AuthControllerAdvisor {

    /**
     * Permet de gérer les erreurs de formulaire. Pour le moment, uniquement signup renvoie ce type d'exception
     * @param exception MethodArgumentNotValidException levée lorsqu'un champ validé par Hibernate n'est pas valide
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadParameters(
            MethodArgumentNotValidException exception, WebRequest request) {

        ErrorsDTO errors = new ErrorsDTO();
        exception.getBindingResult().getAllErrors()
                .stream().filter(FieldError.class::isInstance)
                .map(FieldError.class::cast)
                .forEach(err -> errors.add(new ErrorDTO(err.getField(), err.getDefaultMessage())));

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Permet de gérer les exceptions lié au problème d'intégrité de la db. Pour le moment, utiliser lors du signup lorsqu'
     * on essaie de créer un utilisateur déjà existant
     * @param exception DataIntegrityViolationException levée lorsqu'un id ou un unique est violé
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExists(
            DataIntegrityViolationException  exception, WebRequest request) {
        return new ResponseEntity<>(new BasicErrorDTO("Le nom d'utilisateur est déjà utilisé"), HttpStatus.CONFLICT);
    }

    /**
     * Permet de gérer les exceptions 403. Notamment utilisé, lors d'un mauvais signin
     * on essaie de créer un utilisateur déjà existant
     * @param exception HttpClientErrorException.Forbidden levée lorsqu'un id ou un unique est violé
     * @param request WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> handleForbiddenAccess(
            HttpClientErrorException.Forbidden exception, WebRequest request) {
        return new ResponseEntity<>(new BasicErrorDTO(exception.getStatusText()), HttpStatus.FORBIDDEN);
    }

}
