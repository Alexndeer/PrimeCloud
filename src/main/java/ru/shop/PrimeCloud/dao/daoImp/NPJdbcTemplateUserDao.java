package ru.shop.PrimeCloud.dao.daoImp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Setter
@Getter
public class NPJdbcTemplateUserDao implements UserDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Set<User> findAll() {
        return new HashSet<>(jdbcTemplate.query("SELECT * FROM users", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setName(rs.getString("name"));
            user.setRoles(Roles.getRole(rs.getInt("role")));
            return user;
        }));
    }

    @Override
    public String findNameByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT name FROM users WHERE email = :userEmail",
                Map.of("userEmail", email), String.class);
    }

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = :userEmail",
                Map.of("userEmail", email), (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setName(rs.getString("name"));
                    user.setRoles(Roles.getRole(rs.getInt("role")));
                    return user;
                });
    }

    @Override
    public User insert(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("userEmail", user.getEmail());
        map.addValue("userPassword", user.getPassword());
        map.addValue("userPhone", user.getPhoneNumber());
        map.addValue("userName", user.getName());
        map.addValue("userRole", user.getRoles().ordinal());
        jdbcTemplate.update("INSERT INTO users (email, password, phone_number, name, role) " +
                "VALUES (:userEmail, :userPassword, :userPhone, :userName, :userRole)", map, keyHolder);
        user.setId((Long) keyHolder.getKey());
        return user;
    }

    @Override
    public void removeByEmail(String email) {

    }
}
