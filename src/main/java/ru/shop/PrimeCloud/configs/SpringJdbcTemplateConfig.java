package ru.shop.PrimeCloud.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcUserDao;

import javax.sql.DataSource;
import java.util.zip.DataFormatException;

@Import(DataSourceConfig.class)
@Configuration
public class SpringJdbcTemplateConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDao userDao(){
        JdbcUserDao dao = new JdbcUserDao();
        dao.setDataSource(dataSource);
        return dao;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
