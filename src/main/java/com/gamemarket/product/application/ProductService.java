package com.gamemarket.product.application;

import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.infra.ProductRepository;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(final User user, final ProductCreateRequest request) {
        final Product product = Product.builder()
                .sellerId(user.getId())
                .sellerNickname(user.getNickname())
                .name(request.getName())
                .price(request.getPrice())
                .category(request.getCategory())
                .status(true)
                .build();

        productRepository.save(product);
    }

}
