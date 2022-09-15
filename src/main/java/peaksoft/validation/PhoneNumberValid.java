package peaksoft.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberValid {


    String message() default "Invalid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
