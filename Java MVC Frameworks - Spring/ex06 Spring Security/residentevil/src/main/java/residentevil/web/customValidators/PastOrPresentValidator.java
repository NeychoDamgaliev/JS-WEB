package residentevil.web.customValidators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Created by Neycho Damgaliev on 3/16/2019.
 */
public class PastOrPresentValidator implements ConstraintValidator<PastOrPresent, LocalDate> {

    @Override
    public boolean isValid(LocalDate date,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(date == null) {
            return false;
        }
        LocalDate today = LocalDate.now();
        return date.isBefore(today.minusDays(1));
    }
}
