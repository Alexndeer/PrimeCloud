package ru.shop.PrimeCloud.enums;

public enum Roles {
    Admin(0), Customer(1), Visitor(2);

    private Integer roleId;

    Roles(Integer roleId) {
        this.roleId = roleId;
    }

    public static Roles getRole(Integer value) {
        if (value == 0) return Customer;
        if (value == 1) return Admin;
        else return Visitor;
    }
}
