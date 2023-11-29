package io.growtogether.validation;

import io.growtogether.validation.annotation.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                //Length rule. Min 8 max 128 characters
                new LengthRule(8, 128),
                //At least one upper case letter
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                //At least one lower case letter
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                //At least one number
                new CharacterRule(EnglishCharacterData.Digit, 1),
                //At least one special characters
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule(MatchBehavior.Contains),
                // rejects passwords that contain a sequence of >= 5 characters alphabetical  (e.g. abcdef)
                new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 8, false),
                // rejects passwords that contain a sequence of >= 5 characters numerical   (e.g. 12345)
                new IllegalSequenceRule(EnglishSequenceData.Numerical, 8, false))
        );
        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }
         passwordValidator.getMessages(result).forEach(s ->
                constraintValidatorContext.buildConstraintViolationWithTemplate(s)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation());
        return false;
    }
}
