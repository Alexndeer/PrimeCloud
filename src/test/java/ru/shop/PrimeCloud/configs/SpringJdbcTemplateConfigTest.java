package ru.shop.PrimeCloud.configs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcUserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcUserDao_template;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringJdbcTemplateConfigTest {

    @Test
    public void testSpringJdbcWithH2Db(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JdbcTemplateConfigTest.class);
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        assertNotNull(jdbcTemplate);

        UserDao userDao = ctx.getBean("userDao", UserDao.class);
        System.out.println(userDao.findNameByEmail("gboome8@tamu.edu"));
        System.out.println(userDao.findUserByEmail("gboome8@tamu.edu"));
    }

    @Import(EmbeddedJdbcConfig.class)
    @Configuration
    public static class JdbcTemplateConfigTest{

        @Autowired
        DataSource dataSource;

        @Bean
        public JdbcTemplate jdbcTemplate(){
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            return jdbcTemplate;
        }

        @Bean
        public UserDao userDao(){
            JdbcUserDao_template dao = new JdbcUserDao_template();
            dao.setJdbcTemplate(jdbcTemplate());
            return dao;
        }
    }

}


