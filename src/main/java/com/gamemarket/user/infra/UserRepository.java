package com.gamemarket.user.infra;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.domain.UserUpdateDto;
import com.gamemarket.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static final int UPDATE_SUCCESS = 1;

    public void save(final User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("\"USER\"")
                .usingColumns("nickname", "email", "password", "status", "created_at", "updated_at")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("nickname", user.getNickname());
        parameters.put("password", user.getPassword());
        parameters.put("status", user.getStatus());
        parameters.put("created_at", LocalDateTime.now());
        parameters.put("updated_at", LocalDateTime.now());

        jdbcInsert.execute(new MapSqlParameterSource(parameters));
    }

    public void signOff(final User user) {
        final int updateStatus = jdbcTemplate.update("UPDATE \"USER\" SET status = ? where id = ? and status = ?",user.getStatus() , user.getId(), !user.getStatus());

        if (updateStatus != UPDATE_SUCCESS) {
            throw new UserException(UserExceptionCode.USER_NOT_FOUNT);
        }
    }

    public void existsByNickname(final String nickname) {
        final List<User> users = jdbcTemplate.query("select * from \"USER\" where nickname = ?", userRowMapper(), nickname);

        if (!users.isEmpty()) {
            throw new UserException(UserExceptionCode.EXISTS_USER_NICKNAME);
        }
    }

    public void existsByEmail(final String email) {
        final List<User> users = jdbcTemplate.query("select * from \"USER\" where email = ?", userRowMapper(), email);
        if (!users.isEmpty()) {
            throw new UserException(UserExceptionCode.EXISTS_USER_EMAIL);
        }
    }

    public void existsByUpdateNickname(Long id, String nickname) {
        final List<User> users = jdbcTemplate.query("select * from \"USER\" where nickname = ? and id != ?", userRowMapper(), nickname, id);

        if (!users.isEmpty()) {
            throw new UserException(UserExceptionCode.EXISTS_USER_NICKNAME);
        }
    }

    public User findByEmail(final User user) {
        try {
            return jdbcTemplate.queryForObject("select * from \"USER\" where email = ? and status = ?", userRowMapper(), user.getEmail(), user.getStatus());
        } catch (EmptyResultDataAccessException e) {
            throw new UserException(UserExceptionCode.INVALID_CREDENTIALS);
        }
    }

    public void profileUpdate(final User user) {
        jdbcTemplate.update("UPDATE \"USER\" SET nickname = ?, password = ? WHERE id = ?",
                user.getNickname(), user.getPassword(), user.getId());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setNickname(rs.getString("nickname"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setStatus(rs.getBoolean("status"));

            return user;
        };
    }

}
