package io.growtogether.validation;

import io.growtogether.repository.UserRepository;
import io.growtogether.validation.annotation.EmailExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailInUseValidator implements ConstraintValidator<EmailExist, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !isEmailInUse(email);
    }

    private boolean isEmailInUse(String email) {
        return userRepository.existsByEmail(email);
    }
}
