package ru.shop.PrimeCloud.configs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shop.PrimeCloud.dao.UserDao;
import ru.shop.PrimeCloud.models.User;
import ru.shop.PrimeCloud.util.FillUser;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringJdbcTemplateConfig.class, SpringNPJdbcTemplateConfig.class})
//@ActiveProfiles("JDBCTemplate")
@ActiveProfiles("NPJDBCTemplate")

//TODO: rewrite to get rid of usage tested methods inside other test methods (example - use insert inside testing findUserByName method)
public class SpringJdbcTemplateConfigTest {

    @Autowired
    UserDao userDao;

    @Test
    public void findAllTest() {
        assertEquals(10, userDao.findAll().size());
    }

    @Test
    public void findUserByEmailTestPositive() {
        assertNotNull(userDao);
        User toFind = new User();
        FillUser.fillUser(toFind);
        toFind.setEmail("123@gmail.com");
        userDao.insert(toFind);
        assertEquals(toFind, userDao.findUserByEmail("123@gmail.com"));
    }

    @Test
    public void findUserByEmailTestNegative() {
        assertNotNull(userDao);
        User toFind = new User();
        FillUser.fillUser(toFind);
        toFind.setEmail("123@gmail.com");
        userDao.insert(toFind);
        assertThrows(EmptyResultDataAccessException.class, () -> userDao.findUserByEmail("asd@gmail.com"));
    }

    @Test
    public void insertTestPositive() {
        User toInsert = new User();
        FillUser.fillUser(toInsert);
        toInsert.setEmail("test@gmail.com");
        int size = userDao.findAll().size();
        userDao.insert(toInsert);
        assertEquals(size + 1, userDao.findAll().size());
        assertEquals(toInsert, userDao.findUserByEmail("test@gmail.com"));
    }

    @Test
    public void insertTestNegative() {
        User toInsert = new User();
        FillUser.fillUser(toInsert);
        toInsert.setPassword(null);
        assertThrows(DataIntegrityViolationException.class, () -> userDao.insert(toInsert));
    }

    @Test
    public void findNameByEmailTestPositive(){
//        User user = new User();
//        FillUser.fillUser(user);
//        user.setName("name");
//        user.setEmail("test@gmail.com");
//        userDao.insert(user);
        assertEquals("Jeromy", userDao.findNameByEmail("jcardon2@noaa.gov"));
    }

    @Test
    public void findNameByEmailTestNegative(){
        User user = new User();
        FillUser.fillUser(user);
        user.setName("name");
        user.setEmail("test@gmail.com");
        userDao.insert(user);
        assertNotEquals("name1", userDao.findNameByEmail("test@gmail.com"));
    }

}


