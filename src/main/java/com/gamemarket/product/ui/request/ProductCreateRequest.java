package com.gamemarket.product.ui.request;

import com.gamemarket.product.domain.ProductCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gamemarket.product.utils.ProductConst.PRODUCT_NAME_REGEXP;

@Getter
@Setter
@ToString
public class ProductCreateRequest {

    @NotBlank(message = "상품이름은 필수 입력 입니다.")
    @Pattern(regexp = PRODUCT_NAME_REGEXP, message = "상품이름은 한글 영어 숫자만 입력 가능합니다.")
    private String name;

    @NotNull(message = "카테고리는 필수 입력 입니다.")
    private ProductCategory category;

    @NotNull(message = "상품가격은 필수 입력 입니다.")
    @Positive
    private Integer price;

}
