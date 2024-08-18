package ru.shop.PrimeCloud.repository;

import ru.shop.PrimeCloud.models.User;

import java.util.List;

public interface UserRepo {
    List<User> findAll();

    List<User> findUserByEmail(String email);

    User insert(User user);

    String findNameByEmail(String email);

    void deleteByEmail(String email);
}
