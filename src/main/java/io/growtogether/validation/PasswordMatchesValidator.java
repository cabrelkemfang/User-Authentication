package io.growtogether.validation;

import io.growtogether.dto.UserRegistrationRequest;
import io.growtogether.validation.annotation.PasswordMatches;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationRequest user = (UserRegistrationRequest) obj;
        return StringUtils.equals(user.getPassword(), user.getMatchingPassword());
    }
}
