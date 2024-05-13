package com.gamemarket.product.application;

import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.infra.ProductMapper;
import com.gamemarket.product.infra.ProductRepository;
import com.gamemarket.product.ui.request.ProductCreateRequest;
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

}
