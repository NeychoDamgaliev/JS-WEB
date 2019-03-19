package residentevil.web.customValidators;

import residentevil.web.customValidators.PastOrPresentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
@Constraint(validatedBy = PastOrPresentValidator.class)
public @interface PastOrPresent {
    String message() default "Invalid date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}