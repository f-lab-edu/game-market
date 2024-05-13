package com.gamemarket.common.exception.product;

import com.gamemarket.common.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ExceptionResponse> productExceptionHandler(final ProductException ex, final HttpServletRequest request) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        log.warn("[ProductException] URI: {}, CODE: {}", request.getRequestURI(), response.getCode());

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
