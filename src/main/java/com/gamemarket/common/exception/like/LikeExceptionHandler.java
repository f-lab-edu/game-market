package com.gamemarket.common.exception.like;

import com.gamemarket.common.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LikeExceptionHandler {

    @ExceptionHandler(LikeException.class)
    public ResponseEntity<ExceptionResponse> likeExceptionHandler(final LikeException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
