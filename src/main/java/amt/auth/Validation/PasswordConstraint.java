package amt.auth.Validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraint {
    String message() default "Le mot de passe doit contenir 8 caractères, doit être alphanumérique " +
            "avec au minimum dont 1 caractère spécial, 1 caractère majuscule, 1 caractère minuscule. " +
            "Il est également important d'avoir sacrifié un mouton de laine blanche et  grise ou  noir et grise mais surtout pas blanche et noir ah ça non.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}