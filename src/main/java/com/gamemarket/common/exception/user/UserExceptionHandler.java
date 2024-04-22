package com.gamemarket.common.exception.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userExceptionHandler(final UserException exception, final HttpServletRequest request) {
        final UserExceptionCode code = exception.getCode();
        log.info("[UserException] URI: {}, CODE: {}", request.getRequestURI(), code.getCode());

        return ResponseEntity.status(code.getStatus()).body(code.getMessage());
    }

}
