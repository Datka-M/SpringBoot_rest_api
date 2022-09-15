package peaksoft.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValid, String> {
    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (phoneNumber.length() == 9 || phoneNumber.length() == 13) {
            return phoneNumber.matches("^[0-9]{9,14}$");
        } else {
            return false;
        }
    }
}
