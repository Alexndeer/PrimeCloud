package ru.shop.PrimeCloud.repository.repoImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.shop.PrimeCloud.models.User;
import ru.shop.PrimeCloud.repository.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository("userRepo")
public class UserJdbcRepo implements UserRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserJdbcRepo.class);

    private DataSource dataSource;
    private FindAllUsers findAllUsers;
    private FindUserByEmail findUserByEmail;
    private InsertUser insertUser;
    private DeleteUserByEmail deleteUserByEmail;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.findAllUsers = new FindAllUsers(dataSource);
        this.findUserByEmail = new FindUserByEmail(dataSource);
        this.insertUser = new InsertUser(dataSource);
        this.deleteUserByEmail = new DeleteUserByEmail(dataSource);
    }

    @Override
    public List<User> findAll() {
        return findAllUsers.execute();
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return findUserByEmail.executeByNamedParam(Map.of("email", email));
    }

    @Override
    public User insert(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        insertUser.updateByNamedParam(Map.of("email", user.getEmail(),
                "password", user.getPassword(),
                "phone_number", user.getPhoneNumber(),
                "name", user.getName(),
                "role", user.getRoles().ordinal()), keyHolder);
        Number key = keyHolder.getKey();
        user.setId((Long) key);
        return user;
    }

    @Override
    public String findNameByEmail(String email) {
        return "";
    }

    @Override
    public void deleteByEmail(String email) {
        deleteUserByEmail.updateByNamedParam(Map.of("email", email));
    }
}
