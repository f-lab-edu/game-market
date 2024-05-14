package com.gamemarket.product.ui;

import com.gamemarket.product.application.ProductService;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.infra.ProductRepository;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.product.ui.request.ProductFindRequest;
import com.gamemarket.user.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Product")
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "상품등록")
    public void createProduct(@RequestBody @Valid final ProductCreateRequest request, @RequestAttribute("user") final User user) {
        productService.createProduct(user, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품삭제")
    public void deleteProduct(@PathVariable final Long id, @RequestAttribute("user") final User user) {
        productRepository.deleteProduct(user.getId(), id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "상품조회")
    public List<Product> findProducts(@ModelAttribute @Valid final ProductFindRequest request, @PageableDefault Pageable page) {
        return productRepository.findProducts(request, page);
    }

}
