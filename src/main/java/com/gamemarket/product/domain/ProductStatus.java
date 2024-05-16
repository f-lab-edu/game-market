package com.gamemarket.product.domain;

import com.gamemarket.common.exception.product.ProductException;
import com.gamemarket.common.exception.product.ProductExceptionCode;

import java.util.Arrays;

public enum ProductStatus {

    SALE("SALE"),
    SOLD("SOLD"),
    RESERVED("RESERVED");

    private final String name;

    ProductStatus(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductStatus fromName(final String text) {
        return Arrays.stream(ProductStatus.values())
                .filter(status -> status.name.equals(text))
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.STATUS_NOT_MATCH));
    }

}
