package ru.shop.PrimeCloud.enums;

import lombok.Getter;

@Getter
public enum Roles {
    Customer(0), Admin(1);

    private final int roleId;

    Roles(Integer roleId) {
        this.roleId = roleId;
    }
}
