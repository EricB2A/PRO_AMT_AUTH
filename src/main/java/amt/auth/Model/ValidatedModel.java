package amt.auth.Model;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Abstract class providing a method to validate the model with its @annotation (@Size, @PasswordConstraint, etc.)
 * It throws ConstraintViolationException if invalids
 */
abstract class ValidatedModel {
    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    protected void validateOrThrow() {
        Set<ConstraintViolation<ValidatedModel>> var = validator.validate(this);
        if (var.size() > 0) {
            throw new ConstraintViolationException(var);
        }
    }
}
