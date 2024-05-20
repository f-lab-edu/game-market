package com.gamemarket.product.ui.request;

import com.gamemarket.product.domain.ProductCategory;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gamemarket.product.utils.ProductConst.PRODUCT_NAME_REGEXP;

@Getter
@Setter
@ToString
public class ProductUpdateRequest {

    @Pattern(regexp = PRODUCT_NAME_REGEXP, message = "상품이름은 한글 영어 숫자만 입력 가능합니다.")
    private String name;

    private ProductCategory category;

    @Positive
    private Integer price;

    public boolean isNameUpdate() {
        return !StringUtils.isEmpty(name);
    }

    public boolean isCategoryUpdate() {
        return category != null;
    }

    public boolean isPriceUpdate() {
        return price != null;
    }

}
