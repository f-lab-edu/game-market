package com.gamemarket.product.ui.request;

import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.Sort;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gamemarket.product.utils.ProductConst.PRODUCT_NAME_REGEXP;

@Getter
@Setter
@ToString
public class ProductFindRequest {

    @Pattern(regexp = PRODUCT_NAME_REGEXP, message = "상품이름은 한글 영어 숫자만 입력 가능합니다.")
    private String name;

    @Size(max = 10, message = "닉네임은 10글자 이하이어야 합니다.")
    private String sellerNickname;

    @NotNull(message = "정렬은 필수입력 입니다.")
    private Sort sort;

    private ProductCategory category;
    private Boolean status;
    private Integer limit;
    private Integer offset;

}
