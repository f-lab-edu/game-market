package com.gamemarket.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionResponse {

    final HttpStatus status;
    final int code;
    final String message;

    @Builder
    public ExceptionResponse(HttpStatus status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
