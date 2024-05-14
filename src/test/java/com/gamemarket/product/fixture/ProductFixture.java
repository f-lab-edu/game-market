package com.gamemarket.product.fixture;

import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;

public class ProductFixture {

    public static Product createProduct(
            Long id,
            Long sellerId,
            String sellerNickname,
            String name,
            Integer price,
            ProductCategory category,
            Boolean status
    ) {
        Product product = new Product();
        product.setId(id);
        product.setSellerId(sellerId);
        product.setSellerNickname(sellerNickname);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setStatus(status);

        return product;
    }

    public static String productCreateRequest(String name, ProductCategory category, Integer price) {
        return String.format("{\"name\":\"%s\",\"category\":\"%s\",\"price\":%d}", name, category, price);
    }

}
