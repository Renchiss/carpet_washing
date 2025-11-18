package ru.rrishbuldin.carpet_washing.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rrishbuldin.carpet_washing.dto.ErrorResponseDto;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "Authorization error: " + e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(UserInteractionException.class)
    public ResponseEntity<ErrorResponseDto> handleUserInteractionException(UserInteractionException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, "User interaction error: " + e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponseDto> handleRegistrationException(RegistrationException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Registration failed" + e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception e, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + e.getMessage(), request);
    }

    private ResponseEntity<ErrorResponseDto> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        ErrorResponseDto body = ErrorResponseDto.builder()
                .timestamp(LocalDateTime.now())
                .error(status.getReasonPhrase())
                .status(status.value())
                .message(message)
                .path(request.getRequestURI()).build();
        return ResponseEntity.status(status).body(body);
    }
}