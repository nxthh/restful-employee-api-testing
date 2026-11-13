package co.istad.restfulemployeeapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandling {


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse<?>> handleServiceException(ResponseStatusException e) {

        ErrorResponse<?> body = ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(e.getStatusCode().value())
                .message(e.getReason() != null ? e.getReason() : "Request failed")
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(e.getStatusCode()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleApiValidation(MethodArgumentNotValidException e) {
        List<FieldErrorResponse> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError ->
                        errors.add(
                                FieldErrorResponse.builder()
                                        .field(fieldError.getField())
                                        .message(fieldError.getDefaultMessage())
                                        .build()
                        ));

        return ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
