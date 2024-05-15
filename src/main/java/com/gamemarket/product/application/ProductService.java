package com.gamemarket.product.application;

import com.gamemarket.common.exception.product.ProductException;
import com.gamemarket.common.exception.product.ProductExceptionCode;
import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.infra.ProductMapper;
import com.gamemarket.product.infra.ProductRepository;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.product.ui.request.ProductUpdateRequest;
import com.gamemarket.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void createProduct(final User user, final ProductCreateRequest request) {
        final Product product = productMapper.product(user, request);
        productRepository.save(product);
    }

    public void updateProduct(final Long productId, final Long sellerId, final ProductUpdateRequest request) {
        final Product product = productRepository.findById(productId, sellerId).stream()
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.USER_PRODUCT_NOT_FOUND));

        System.out.println("product = " + product);

        final String name = getUpdateName(product, request);
        final ProductCategory category = getUpdateCategory(product, request);
        final Integer price = getUpdatePrice(product, request);
        final Product updateProduct = productMapper.updateProduct(name, price, category);

        System.out.println("updateProduct = " + updateProduct);

        productRepository.updateProduct(product.getId(), product.getSellerId(), updateProduct);
    }

    private String getUpdateName(final Product product, final ProductUpdateRequest request) {
        if (request.isNameUpdate()) {
            return request.getName();
        }
        return product.getName();
    }

    private ProductCategory getUpdateCategory(final Product product, final ProductUpdateRequest request) {
        if (request.isCategoryUpdate()) {
            return request.getCategory();
        }
        return product.getCategory();
    }

    private Integer getUpdatePrice(final Product product, final ProductUpdateRequest request) {
        if (request.isPriceUpdate()) {
            return request.getPrice();
        }
        return product.getPrice();
    }

}
