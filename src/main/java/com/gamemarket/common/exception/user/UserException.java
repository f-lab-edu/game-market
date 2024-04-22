package com.gamemarket.common.exception.user;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private final UserExceptionCode code;

    public UserException(final UserExceptionCode code) {
        this.code = code;
    }

}
