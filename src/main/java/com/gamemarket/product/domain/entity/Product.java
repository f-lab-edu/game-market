package com.gamemarket.product.domain.entity;

import com.gamemarket.product.domain.ProductCategory;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

    private Long id;
    private Long sellerId;
    private String sellerNickname;
    private String name;
    private Integer price;
    private ProductCategory category;
    private Boolean status;

    @Builder
    public Product(
            final Long sellerId,
            final String sellerNickname,
            final String name,
            final Integer price,
            final ProductCategory category,
            final Boolean status
    ) {
        this.sellerId = sellerId;
        this.sellerNickname = sellerNickname;
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
    }

}
