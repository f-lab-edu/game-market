package com.gamemarket.like.infra;

import com.gamemarket.common.exception.like.LikeException;
import com.gamemarket.common.exception.like.LikeExceptionCode;
import com.gamemarket.like.domain.entity.ProductLike;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductLikeRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(final ProductLike productLike) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product_like")
                .usingColumns("product_id", "user_id", "seller_id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("productId", productLike.getProductId());
        parameters.put("userId", productLike.getUserId());
        parameters.put("sellerId", productLike.getSellerId());

        try {
            jdbcInsert.execute(new MapSqlParameterSource(parameters));
        } catch (DataIntegrityViolationException e) {
            throw new LikeException(LikeExceptionCode.DUPLICATE_PRODUCT_LIKE);
        }
    }

}
