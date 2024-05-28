package com.gamemarket.like.infra;

import com.gamemarket.like.domain.entity.ProductLike;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductLikeMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.sellerId", target = "sellerId")
    ProductLike productLike(final Product product, final User user);

}
