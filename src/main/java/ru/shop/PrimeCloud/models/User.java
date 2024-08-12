package ru.shop.PrimeCloud.models;

import lombok.Getter;
import lombok.Setter;
import ru.shop.PrimeCloud.enums.Roles;

@Getter
@Setter
public class User {

    private long id;
    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Roles roles;
}
