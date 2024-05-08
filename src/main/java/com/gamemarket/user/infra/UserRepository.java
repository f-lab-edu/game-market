package com.gamemarket.user.infra;

import com.gamemarket.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
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
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(final User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("\"USER\"")
                .usingColumns("nickname", "email", "password", "status", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("nickname", user.getNickname());
        parameters.put("password", user.getPassword());
        parameters.put("status", user.isStatus());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public Boolean existsByNickname(final String nickname) {
        final List<User> users = jdbcTemplate.query("select * from \"USER\" where nickname = ?", userRowMapper(), nickname);
        return !users.isEmpty();
    }

    public Boolean existsByEmail(final String email) {
        final List<User> users = jdbcTemplate.query("select * from \"USER\" where email = ?", userRowMapper(), email);
        return !users.isEmpty();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setNickname(rs.getString("nickname"));
            user.setEmail(rs.getString("email"));

            return user;
        };
    }

}
