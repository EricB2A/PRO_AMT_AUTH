package amt.auth.Exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException (String msg)
    {
        super(msg);
    }
}
