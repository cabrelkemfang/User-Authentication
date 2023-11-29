package io.growtogether.mapper;

import io.growtogether.dto.ValidationError;
import org.springframework.stereotype.Component;

@Component
public class ValidatorErrorMapper {

    public ValidationError mapToValidatorError(String message, String field) {
        return ValidationError.builder()
                .message(message)
                .code(field)
                .build();
    }
}
