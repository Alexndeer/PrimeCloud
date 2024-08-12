package ru.shop.PrimeCloud.dao.daoImp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shop.PrimeCloud.configs.EmbeddedJdbcConfig;
import ru.shop.PrimeCloud.configs.SpringDataSourceConfig;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.models.User;

import javax.sql.DataSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JdbcUserDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    private EmbeddedDatabase db;

    @BeforeEach
    public void setUp(){
//        db = new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScripts("classpath:db/h2/schema.sql",
//                        "classpath:db/h2/test-data.sql").build();
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScripts("classpath:db/h2/schema.sql")
                .build();
    }

    @Test
    void findAll() {
//        JdbcUserDao userDao = new JdbcUserDao();
//        userDao.setDataSource(db);
//        assertEquals(userDao.findAll().size(), 10);
    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void insertPositive() {
//        assertNull(userdao.insert(new User()));
    }

    @Test
    void insertNegative(){

    }
}