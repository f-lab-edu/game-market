package com.gamemarket.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionResponse {

    final private int code;
    final private String message;

    @Builder
    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
