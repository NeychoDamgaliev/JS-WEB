package residentevil.web.customValidators;

import residentevil.web.domain.enums.Creator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
public class NameValidator implements ConstraintValidator<CreatorNameValidation, Creator> {
    @Override
    public boolean isValid(Creator name, ConstraintValidatorContext constraintValidatorContext) {
        if(name != null && name != null) {
            return true;
        }
        return false;
    }
}
