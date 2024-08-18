package ru.shop.PrimeCloud.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcTemplateUserDao;
import ru.shop.PrimeCloud.dao.daoImp.NPJdbcTemplateUserDao;

import javax.sql.DataSource;

@Import(EmbeddedJdbcConfig.class)
@Configuration
@Profile("NPJDBCTemplate")
public class SpringNPJdbcTemplateConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public UserDao userDao(){
        NPJdbcTemplateUserDao dao = new NPJdbcTemplateUserDao();
        dao.setJdbcTemplate(jdbcTemplate());
        return dao;
    }
}
