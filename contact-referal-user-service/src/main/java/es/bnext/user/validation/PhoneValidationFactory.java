package es.bnext.user.validation;

import io.micronaut.context.annotation.Factory;
import io.micronaut.validation.validator.constraints.ConstraintValidator;

import javax.inject.Singleton;
import java.util.regex.Pattern;

@Factory
public class PhoneValidationFactory {
    @Singleton
    ConstraintValidator<PhoneValidation, String> nameAndLastNameValidator() {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        return (value, annotationMetadata, context) -> value != null && (pattern.matcher(value).matches());
    }

}
