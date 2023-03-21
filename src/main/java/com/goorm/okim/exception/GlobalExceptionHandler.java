package com.goorm.okim.exception;

import com.goorm.okim.common.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    protected ResponseEntity<?> handleBusinessLogicException(BusinessLogicException e) {
        return Response.fail(e.getErrorCodeMessage().getCode(), e.getErrorCodeMessage().getMessage());
    }
}
