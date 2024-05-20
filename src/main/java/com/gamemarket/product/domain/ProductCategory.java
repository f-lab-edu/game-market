package com.gamemarket.product.domain;

import com.gamemarket.common.exception.product.ProductException;
import com.gamemarket.common.exception.product.ProductExceptionCode;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum ProductCategory {

    ACTION("ACTION"),
    ADVENTURE("ADVENTURE"),
    CASUAL("CASUAL"),
    INDIE("INDIE"),
    MMORPG("MMORPG"),
    SPORTS("SPORTS"),
    RACING("RACING"),
    SIMULATION("SIMULATION"),
    STRATEGY("STRATEGY");

    private final String name;

    ProductCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ProductCategory fromName(final String text) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.name.equals(text))
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.CATEGORY_NOT_MATCH));
    }

}
