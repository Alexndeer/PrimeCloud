package ru.shop.PrimeCloud.dao;

public class UserDaoQueryConstants {

    public static final String ALL_SELECT = "select * from users";
    public static final String SIMPLE_INSERT = "insert into users (email, password, phone_number, name, role) values (?, ?, ?, ?, ?)";
}
