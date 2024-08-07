package ru.shop.PrimeCloud.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.shop.PrimeCloud.dao.JdbcSingerDao;
import ru.shop.PrimeCloud.dao.UserDao;

import javax.sql.DataSource;

@Import(BasicDataSourceConfig.class)
@Configuration
public class DataSourceConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDao userDao() {
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setDataSource(dataSource);
        return dao;
    }
}
