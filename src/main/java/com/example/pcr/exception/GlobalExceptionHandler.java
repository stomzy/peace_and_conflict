package com.example.pcr.exception;

import com.example.pcr.exception.custom.CustomAppException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiError apiError = new ApiError(400, "Validation failed", errors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(CustomAppException.class)
    public ResponseEntity<ApiError> handleCustomAppException(CustomAppException ex) {
        ApiError apiError = new ApiError(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError(400, ex.getMessage());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex) {
        ex.printStackTrace(); // optional for debugging
        ApiError apiError = new ApiError(500, "Internal Server Error");
        return ResponseEntity.internalServerError().body(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException ex) {
        ApiError apiError = new ApiError(401, "Invalid username or password");
        log.warn("Authentication failed: {}", ex.getMessage());
        return ResponseEntity.status(401).body(apiError);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiError> handleFileSizeException(MultipartException ex) {
        ApiError apiError = new ApiError(400, "File size is too large. Please upload a smaller file.");
        return ResponseEntity.status(400).body(apiError);
    }

}
