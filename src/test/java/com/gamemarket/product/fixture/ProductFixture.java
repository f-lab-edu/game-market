package com.gamemarket.product.fixture;

import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.product.ui.request.ProductFindRequest;
import com.gamemarket.product.ui.request.ProductUpdateRequest;

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

    public static ProductCreateRequest productCreateRequest(String name, ProductCategory category, Integer price) {
        return new ProductCreateRequest(name, category, price);
    }

    public static ProductFindRequest productFindRequest(String name) {
        ProductFindRequest productFindRequest = new ProductFindRequest();
        productFindRequest.setName(name);

        return productFindRequest;
    }

    public static ProductUpdateRequest productUpdateRequest(String name) {
        return new ProductUpdateRequest(name, null, null);
    }

    public static String productRequest(String name, ProductCategory category, Integer price) {
        return String.format("{\"name\":\"%s\",\"category\":\"%s\",\"price\":%d}", name, category, price);
    }

}
