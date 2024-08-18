package ru.shop.PrimeCloud.dao.daoImp;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@Setter
@Getter
public class JdbcTemplateUserDao implements UserDao {
    private JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.queryForObject("SELECT name FROM users WHERE email = ?", String.class, email);
    }

    @Override
    public void removeByEmail(String email) {

    }

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email = ?", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong(1));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setName(rs.getString("name"));
            user.setRoles(Roles.getRole(rs.getInt("role")));
            return user;
        }, email);
    }

    @Override
    public User insert(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO users (email, password, phone_number, name, role) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getName());
            stmt.setInt(5, user.getRoles().ordinal());
            return stmt;
        }, keyHolder);
        user.setId((Long) keyHolder.getKey());
        return user;
    }
}
