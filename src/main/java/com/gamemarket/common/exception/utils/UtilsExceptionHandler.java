package com.gamemarket.common.exception.utils;

import com.gamemarket.common.exception.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UtilsExceptionHandler {

    @ExceptionHandler(UtilsException.class)
    public ResponseEntity<ExceptionResponse> jsonExceptionHandler(final UtilsException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message("server error")
                .build();

        log.error("[UtilsException] CODE: {}", response.getCode(), ex);

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
