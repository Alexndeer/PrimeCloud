package ru.shop.PrimeCloud.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcTemplateUserDao;

import javax.sql.DataSource;
import java.util.zip.DataFormatException;

@Import(EmbeddedJdbcConfig.class)
@Configuration
public class SpringJdbcTemplateConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public UserDao userDao(){
        JdbcTemplateUserDao dao = new JdbcTemplateUserDao();
        dao.setJdbcTemplate(jdbcTemplate());
        return dao;
    }
}
