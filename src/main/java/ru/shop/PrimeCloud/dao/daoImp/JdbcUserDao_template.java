package ru.shop.PrimeCloud.dao.daoImp;

import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@Component
@Setter
public class JdbcUserDao_template implements UserDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Set<User> findAll() {
        return Set.of();
    }

    @Override
    public String findNameByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT name FROM users WHERE email = ?", String.class, email);
    }

    @Override
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT id, password FROM users WHERE email = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, email);
//        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("SELECT * FROM users WHERE email = ?", email);
//        for (String tmp : stringObjectMap.keySet()) {
//            System.out.println(tmp);
//        }
//        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }
}
