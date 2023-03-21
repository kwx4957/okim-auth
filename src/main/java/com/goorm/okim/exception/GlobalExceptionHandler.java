package com.goorm.okim.exception;

import com.goorm.okim.common.Response;
import com.goorm.okim.exception.auth.EmailNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.goorm.okim.exception.ErrorCodeMessage.AUTH_DISABLED;
import static com.goorm.okim.exception.ErrorCodeMessage.AUTH_WRONG_PASSWORD;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    protected ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e) {
        return Response.badRequest(e.getErrorCodeMessage().getCode(), e.getErrorCodeMessage().getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<?> handleAuthenticationException(AuthenticationException e) {
        return switch (e.getClass().getSimpleName()) {
            case "EmailNotFoundException" -> Response.unAuthorize(
                    ((EmailNotFoundException) e).getErrorCodeMessage().getCode(),
                    ((EmailNotFoundException) e).getErrorCodeMessage().getMessage());
            case "DisabledException" -> Response.unAuthorize(AUTH_DISABLED.getCode(), AUTH_DISABLED.getMessage());
            default -> Response.unAuthorize(AUTH_WRONG_PASSWORD.getCode(), AUTH_WRONG_PASSWORD.getMessage());
        };
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return Response.toFailObject(message);
    }
}
