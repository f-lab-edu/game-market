package com.gamemarket.common.exception.user;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserExceptionCode {

    EXISTS_USER_EMAIL(HttpStatus.BAD_REQUEST, 2000,  "이미 존재하는 이메일 입니다."),
    EXISTS_USER_NICKNAME(HttpStatus.BAD_REQUEST, 2001, "이미 존재하는 닉네임 입니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    UserExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
