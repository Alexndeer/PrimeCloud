package ru.shop.PrimeCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.shop.PrimeCloud.dao.JdbcSingerDao;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;

@SpringBootApplication
public class PrimeCloudApplication {

    public static void main(String[] args) {
        System.out.println("sanya 4e4en");
        ConfigurableApplicationContext ctx = SpringApplication.run(PrimeCloudApplication.class, args);

//        User user = new User();
//        user.setName("san9");
//        user.setEmail("sanya@mail.ru");
//        user.setPassword("123456");
//        user.setPhoneNumber("313232");
//        user.setRoles(Roles.Customer);
//
//        UserDao userDao = ctx.getBean("userDao", UserDao.class);
//        User insertedUser = userDao.insert(user);
//        System.out.println(insertedUser.getId());
    }

}
