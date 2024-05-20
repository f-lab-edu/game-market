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

}
