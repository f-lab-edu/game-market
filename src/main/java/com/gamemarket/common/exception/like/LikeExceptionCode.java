package com.gamemarket.common.exception.like;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum LikeExceptionCode {

    DUPLICATE_PRODUCT_LIKE(HttpStatus.CONFLICT, 4000,  "이미 존재하는 상품 좋아요 입니다."),
    LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, 4001, "좋아요가 존재하지 않습니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    LikeExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
