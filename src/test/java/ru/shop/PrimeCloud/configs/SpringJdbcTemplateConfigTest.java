package ru.shop.PrimeCloud.configs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.models.User;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringJdbcTemplateConfig.class)
public class SpringJdbcTemplateConfigTest {

    @Autowired
    UserDao userDao;

    @Test
    public void findAllTest() {
        assertEquals(10, userDao.findAll().size());
//        userDao.findAll().forEach(user -> System.out.println(user));
    }

    @Test
    public void findNameByEmailTest() {
        assertNotNull(userDao);
        userDao.findUserByEmail("gboome8@tamu.edu");
    }


//    @Test
//    public void testSpringJdbcWithH2Db(){
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(S.class);
//        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
//        assertNotNull(jdbcTemplate);
//
//        UserDao userDao = ctx.getBean("userDao", UserDao.class);
//        assertEquals("Gerta",);
//        System.out.println(userDao.findNameByEmail("gboome8@tamu.edu"));
//        System.out.println(userDao.findUserByEmail("gboome8@tamu.edu"));
//
//        User user = new User();
//        user.setEmail("tobi75500@gmail.com");
//        user.setPassword("123456");
//        user.setPhoneNumber("8800553535");
//        user.setName("Vlad");
//        user.setRoles(Roles.Admin);
//        System.out.println(userDao.insert(user));
//    }

}


