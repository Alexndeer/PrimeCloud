package ru.shop.PrimeCloud.dao.daoImp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shop.PrimeCloud.configs.SpringDataSourceConfig;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.models.User;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataSourceConfig.class)
class JdbcUserDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUserDao.class);

    @Autowired
    private UserDao userdao;

    @Test
    void findAll() {

    }

    @Test
    void findUserByEmail() {
    }

    @Test
    void insert() {
        assertNull(userdao.insert(new User()));
    }
}