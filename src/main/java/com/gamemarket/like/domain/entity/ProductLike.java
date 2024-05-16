package com.gamemarket.like.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductLike {

    private Long productId;
    private Long userId;
    private Long sellerId;

}
