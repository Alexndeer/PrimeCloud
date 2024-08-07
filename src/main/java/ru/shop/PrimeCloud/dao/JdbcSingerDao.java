package ru.shop.PrimeCloud.dao;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static ru.shop.PrimeCloud.dao.UserDaoQueryConstants.ALL_SELECT;
import static ru.shop.PrimeCloud.dao.UserDaoQueryConstants.SIMPLE_INSERT;

@Setter
public class JdbcSingerDao implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDao.class);

    private DataSource dataSource;

    @Override
    public Set<User> findAll() {
        Set<User> result = new HashSet<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ALL_SELECT);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setName(rs.getString("name"));
                user.setRoles(Roles.valueOf(rs.getString("role")));
                result.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Problem executing SELECT", e);
        }
        return result;
    }

    @Override
    public User findByName() {
        return null;
    }

    @Override
    public boolean findByEmail(String email) {
        return false;
    }

    @Override
    public User findByPhoneNumber() {
        return null;
    }

    @Override
    public User insert(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SIMPLE_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getName());
            statement.setInt(5, user.getRoles().getRoleId());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Problem executing INSERT", e);
        }
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
