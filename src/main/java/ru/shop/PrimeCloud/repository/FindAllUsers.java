package ru.shop.PrimeCloud.repository;

import org.springframework.jdbc.object.MappingSqlQuery;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllUsers extends MappingSqlQuery<User> {

    public FindAllUsers(DataSource dataSource){
        super(dataSource, "SELECT * FROM users");
    }

    @Override
    protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setName(rs.getString("name"));
        user.setRoles(Roles.getRole(rs.getInt("role")));
        return user;
    }
}
