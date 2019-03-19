package residentevil.web.customValidators;

import residentevil.web.customValidators.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Neycho Damgaliev on 3/17/2019.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = NameValidator.class)
public @interface CreatorNameValidation {

    String message() default "Invalid creator name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
