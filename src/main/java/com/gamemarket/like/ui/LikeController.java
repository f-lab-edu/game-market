package com.gamemarket.like.ui;

import com.gamemarket.like.domain.entity.ProductLike;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.like.infra.ProductLikeMapper;
import com.gamemarket.like.infra.ProductLikeRepository;
import com.gamemarket.product.infra.ProductRepository;
import com.gamemarket.user.domain.CurrentUser;
import com.gamemarket.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Like")
@RequestMapping("/like")
public class LikeController {

    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductLikeMapper productLikeMapper;

    @PostMapping("/product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "좋아요 - 상품")
    public void createProductLike(@PathVariable final Long id, @CurrentUser User user) {
        final Product product = productRepository.findById(id);
        final ProductLike productLike = productLikeMapper.productLike(product, user);

        productLikeRepository.findProductLike(productLike);
        productLikeRepository.createProductLike(productLike);
    }

    @DeleteMapping("/product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "좋아요 삭제 - 상품")
    public void deleteProductLike(@PathVariable final Long id, @CurrentUser User user) {
        final Product product = productRepository.findById(id);
        final ProductLike productLike = productLikeMapper.productLike(product, user);

        productLikeRepository.deleteProductLike(productLike);
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "좋아요 조회 - 상품")
    public List<ProductLike> findAllProductLike(@PathVariable final Long id) {
        return productLikeRepository.findAllProductLike(id);
    }

}
