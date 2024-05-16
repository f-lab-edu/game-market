package com.gamemarket.common.exception.product;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductExceptionCode {

    USER_PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, 3000, "해당 유저의 상품이 존재하지 않습니다."),
    CATEGORY_NOT_MATCH(HttpStatus.INTERNAL_SERVER_ERROR, 3001, "ENUM match error"),
    STATUS_NOT_MATCH(HttpStatus.INTERNAL_SERVER_ERROR, 3002, "ENUM match error");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ProductExceptionCode(final HttpStatus status, final int code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
