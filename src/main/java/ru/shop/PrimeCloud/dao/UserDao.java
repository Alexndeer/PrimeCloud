package ru.shop.PrimeCloud.dao;

import ru.shop.PrimeCloud.models.User;

import java.util.Set;

public interface UserDao {

    Set<User> findAll();

    User findUserByEmail(String email);

    User insert(User user);

    String findNameByEmail(String email);

    public void removeByEmail(String email);
}
