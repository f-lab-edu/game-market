package com.gamemarket.common.exception.utils;

import lombok.Getter;

@Getter
public class UtilsException extends RuntimeException {

    private final UtilsExceptionCode code;

    public UtilsException(final UtilsExceptionCode code) {
        this.code = code;
    }

}
