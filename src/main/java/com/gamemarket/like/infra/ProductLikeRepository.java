package com.gamemarket.like.infra;

import com.gamemarket.common.exception.like.LikeException;
import com.gamemarket.common.exception.like.LikeExceptionCode;
import com.gamemarket.like.domain.entity.ProductLike;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductLikeRepository {

    private final JdbcTemplate jdbcTemplate;

    public void createProductLike(final ProductLike productLike) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product_like")
                .usingColumns("product_id", "user_id", "seller_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("productId", productLike.getProductId());
        parameters.put("userId", productLike.getUserId());
        parameters.put("sellerId", productLike.getSellerId());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public void findProductLike(final ProductLike productLike) {
        Integer count = jdbcTemplate.queryForObject("select count(*) from product_like where product_id = ? and user_id = ? and seller_id = ?",
                Integer.class, productLike.getProductId(), productLike.getUserId(), productLike.getSellerId());

        if ((count != null) && (count > 0)) {
            throw new LikeException(LikeExceptionCode.DUPLICATE_PRODUCT_LIKE);
        }
    }

    public void deleteProductLike(final ProductLike productLike) {
        int deleteProductLike = jdbcTemplate.update("delete from product_like where product_id = ? and user_id = ? and seller_id = ?",
                productLike.getProductId(), productLike.getUserId(), productLike.getSellerId());

        if (deleteProductLike == 0) {
            throw new LikeException(LikeExceptionCode.LIKE_NOT_FOUND);
        }
    }

    public List<ProductLike> findAllProductLike(final Long productId) {
        return jdbcTemplate.query("select * from product_like where product_id = ?", productLikeRowMapper(), productId);
    }

    private RowMapper<ProductLike> productLikeRowMapper() {
        return (rs, rowNum) -> {
            ProductLike productLike = new ProductLike();
            productLike.setProductId(rs.getLong("product_id"));
            productLike.setUserId(rs.getLong("user_id"));
            productLike.setSellerId(rs.getLong("seller_id"));

            return productLike;
        };
    }

}
