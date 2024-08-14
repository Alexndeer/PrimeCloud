package ru.shop.PrimeCloud.dao.daoImp;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.PrimeCloud.configs.DataSourceConfig;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Component
public class JdbcUserDao implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    @Autowired
    private DataSource dataSource;

    @Override
    public Set<User> findAll() {
        Set<User> userSet = new HashSet<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRoles(Roles.getRole(rs.getInt("role")));
                userSet.add(user);
            }
            return userSet;
        } catch (SQLException e) {
            LOGGER.error("Problem when executing SELECT *", e);
        }
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = new User();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email=" + email);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
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
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (email, password, phone_number, name, role) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getName());
            stmt.setInt(5, user.getRoles().ordinal());
            stmt.execute();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Problem when executing INSERT!", e);
        }
        return null;
    }

    @Override
    public String findNameByEmail(String email) {
        return "";
    }
}
