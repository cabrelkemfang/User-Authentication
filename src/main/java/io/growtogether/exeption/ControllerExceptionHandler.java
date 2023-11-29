package io.growtogether.exeption;

import io.growtogether.dto.ErrorDetailResponse;
import io.growtogether.mapper.ErrorDetailResponseMapper;
import io.growtogether.mapper.ValidatorErrorMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {
    private static final String DEFAULT_MESSAGE = "unhandled server message";
    private final ValidatorErrorMapper validatorErrorMapper;
    private final ErrorDetailResponseMapper errorDetailResponseMapper;

    @ExceptionHandler(AuthenticationBackendException.class)
    public ResponseEntity<ErrorDetailResponse> resourceNotFoundException(AuthenticationBackendException ex, WebRequest request) {
        log.warn(" Error Message {}", ExceptionUtils.getMessage(ex));
        var message = this.errorDetailResponseMapper.mapToErrorDetailResponse(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(message, HttpStatus.valueOf(ex.getCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailResponse> globalExceptionHandler(Exception ex, WebRequest request) {
        log.warn(" Error Message {}", ExceptionUtils.getMessage(ex));
        var message = this.errorDetailResponseMapper.mapToErrorDetailResponse(DEFAULT_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException exception, HttpServletRequest request) {
        log.warn(" Error Message {}", ExceptionUtils.getMessage(exception));
        var errorDetailResponse = this.errorDetailResponseMapper.mapToErrorDetailResponse("Validation Error", HttpStatus.BAD_REQUEST.value());

        List<ObjectError> fieldErrors = exception.getBindingResult().getAllErrors();
        var validationErrorList = fieldErrors.stream()
                .map(fieldError -> this.validatorErrorMapper.mapToValidatorError(fieldError.getDefaultMessage(), fieldError.getCode()))
                .toList();

        errorDetailResponse.setErrors(validationErrorList);
        return new ResponseEntity<>(errorDetailResponse, HttpStatus.BAD_REQUEST);
    }

}
