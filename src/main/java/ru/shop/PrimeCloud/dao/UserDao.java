package ru.shop.PrimeCloud.dao;

import ru.shop.PrimeCloud.models.User;

import java.util.Set;

public interface UserDao {

    Set<User> findAll();

    User findByName();

    User findByPhoneNumber();

    User insert(User user);

    boolean findByEmail(String email);

    void update(User user);

    void delete(User user);
}
