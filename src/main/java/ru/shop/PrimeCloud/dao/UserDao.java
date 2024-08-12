package ru.shop.PrimeCloud.dao;

import ru.shop.PrimeCloud.models.User;

import java.util.Set;

public interface UserDao {

    Set<User> findAll();

    User findUserByEmail(String email);

    User insert(User user);
}
