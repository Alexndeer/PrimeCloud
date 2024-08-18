package ru.shop.PrimeCloud.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import ru.shop.PrimeCloud.enums.Roles;

import java.util.Objects;

@Getter
@Setter
@ToString
public class User {

    private long id;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private Roles roles = Roles.Visitor;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return Objects.equals(email, ((User) obj).email);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(phoneNumber, user.phoneNumber) && roles == user.roles;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, phoneNumber, roles);
    }
}
