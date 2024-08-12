package ru.shop.PrimeCloud.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.dao.daoImp.JdbcUserDao;

import javax.sql.DataSource;

@Import(DataSourceConfig.class)
@Configuration
public class SpringDataSourceConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringDataSourceConfig.class);

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDao userDao() {
        JdbcUserDao dao = new JdbcUserDao();
        dao.setDataSource(dataSource);
        return dao;
    }
}
