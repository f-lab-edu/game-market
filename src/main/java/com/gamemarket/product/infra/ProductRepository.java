package com.gamemarket.product.infra;

import com.gamemarket.common.exception.product.ProductException;
import com.gamemarket.common.exception.product.ProductExceptionCode;
import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.ui.request.ProductFindRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(final Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product")
                .usingColumns("seller_id", "seller_nickname", "name", "price", "category", "status", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("sellerId", product.getSellerId());
        parameters.put("sellerNickname", product.getSellerNickname());
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("category", product.getCategory());
        parameters.put("status", product.getStatus());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public void deleteProduct(final Long userId, final Long productId) {
        int deleteProductCount = jdbcTemplate.update("delete from product where id = ? and seller_id = ?", productId, userId);

        if (deleteProductCount == 0) {
            throw new ProductException(ProductExceptionCode.USER_PRODUCT_NOT_FOUND);
        }
    }

    public List<Product> findProducts(final ProductFindRequest request, final Pageable page) {
        final String dynamicQuery = createDynamicQuery(request, page);
        return jdbcTemplate.query(dynamicQuery, productRowMapper());
    }

    public Product findProduct(final Long productId, final Long sellerId) {
        final List<Product> products = jdbcTemplate.query("select * from product where id = ? and seller_id = ?", productRowMapper(), productId, sellerId);
        return products.stream()
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.USER_PRODUCT_NOT_FOUND));
    }

    public Product findById(final Long productId) {
        final List<Product> products = jdbcTemplate.query("select * from product where id = ?", productRowMapper(), productId);
        return products.stream()
                .findFirst()
                .orElseThrow(() -> new ProductException(ProductExceptionCode.USER_PRODUCT_NOT_FOUND));
    }

    public void updateProduct(final Long productId, final Long sellerId, final Product product) {
        jdbcTemplate.update("update product set name = ?, price = ?,category = ? where id = ? and seller_id = ?",
                product.getName(), product.getPrice(), product.getCategory().name(), productId, sellerId);
    }

    private String createDynamicQuery(final ProductFindRequest request, final Pageable page) {
        StringBuilder query = new StringBuilder("select * from product where 1=1");

        if (request.getCategory() != null) {
            query.append(" and category = '").append(request.getCategory().name()).append("'");
        }

        if (request.getStatus() != null) {
            if (request.getStatus()) {
                query.append(" and status = true");
            } else {
                query.append(" and status = false");
            }
        }

        if (request.getName() != null) {
            query.append(" and name = '").append(request.getName()).append("'");
        }

        if (request.getSellerNickname() != null) {
            query.append(" and seller_nickname = '").append(request.getSellerNickname()).append("'");
        }

        query.append(" order by ");
        page.getSort().forEach(order -> {
            query.append(order.getProperty()).append(" ").append(order.getDirection());
        });
        query.append(" limit ").append(page.getPageNumber()).append(", ").append(page.getPageSize());

        return query.toString();
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setSellerId(rs.getLong("seller_id"));
            product.setSellerNickname(rs.getString("seller_nickname"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setCategory(ProductCategory.fromName(rs.getString("category")));
            product.setStatus(rs.getBoolean("status"));

            return product;
        };
    }

}
