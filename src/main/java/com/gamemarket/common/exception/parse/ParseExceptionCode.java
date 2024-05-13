package com.gamemarket.common.exception.parse;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ParseExceptionCode {

    JSON_PARSER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "JSON parsing error"),
    ENUM_PARSER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, 1001, "ENUM parsing error");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ParseExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
