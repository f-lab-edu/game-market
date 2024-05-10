package com.gamemarket.common.exception.product;

import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {

    private final ProductExceptionCode code;

    public ProductException(final ProductExceptionCode code) {
        this.code = code;
    }

}
