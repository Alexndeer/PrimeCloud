package ru.shop.PrimeCloud.dao.daoImp;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.shop.PrimeCloud.configs.DataSourceConfig;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

@Setter
@Component
public class JdbcUserDao implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);
    private DataSource dataSource;

    @Override
    public Set<User> findAll() {
        return Set.of();
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email=" + email);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setEmail(email);
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRoles(Roles.getRole(rs.getInt("role")));
            }
            return user;
        } catch (Exception e) {
           LOGGER.error("Problem when executing SELECT!", e);
        }
        return null;
    }

    @Override
    public User insert(User user) {
        return null;
    }
}
