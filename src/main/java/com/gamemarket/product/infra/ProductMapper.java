package com.gamemarket.product.infra;

import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.user.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "user.id", target = "sellerId")
    @Mapping(source = "user.nickname", target = "sellerNickname")
    @Mapping(source = "request.name", target = "name")
    @Mapping(source = "request.price", target = "price")
    @Mapping(source = "request.category", target = "category")
    Product product(final User user, final ProductCreateRequest request);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "category", target = "category")
    Product updateProduct(final String name, final Integer price, final ProductCategory category);

}
