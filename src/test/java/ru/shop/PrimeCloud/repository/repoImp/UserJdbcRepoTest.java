package ru.shop.PrimeCloud.repository.repoImp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.shop.PrimeCloud.configs.EmbeddedJdbcConfig;
import ru.shop.PrimeCloud.configs.EmptyEmbeddedJdbcConfig;
import ru.shop.PrimeCloud.enums.Roles;
import ru.shop.PrimeCloud.models.User;
import ru.shop.PrimeCloud.repository.UserRepo;

import static org.junit.jupiter.api.Assertions.*;

@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({"classpath:db/h2/drop-schema.sql", "classpath:db/h2/create-schema.sql"})
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {EmptyEmbeddedJdbcConfig.class, UserJdbcRepo.class})
class UserJdbcRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    @Sql(value = "classpath:db/h2/test-data.sql",
        config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"),
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void findAllTest() {
        assertEquals(10, userRepo.findAll().size());
    }

    @Test
    @DisplayName("findUserByEmail")
    @Sql(statements = "INSERT INTO users (email, password, phone_number, name, role) VALUES " +
            "('tobi75500@gmail.com', '123456', '31-31-31', 'vlad', 0)")
//    @Sql(value = "classpath:db/h2/test-data.sql")
    public void findUserByEmail(){
        System.out.println(userRepo.findUserByEmail("tobi75500@gmail.com"));
    }

    @Test
    public void insert(){
        User user = new User();
        user.setEmail("123");
        user.setPassword("123456");
        user.setPhoneNumber("12-34-56");
        user.setName("vlad");
        user.setRoles(Roles.Admin);
        userRepo.insert(user);
        System.out.println(userRepo.findAll());
    }

    @Test
    @DisplayName("removeUserByEmail")
    @Sql(statements = "INSERT INTO users (email, password, phone_number, name, role) VALUES " +
            "('tobi75500@gmail.com', '123456', '31-31-31', 'vlad', 0)")
    public void removeUserByEmail(){
        System.out.println(userRepo.findAll());
        assertEquals(1, userRepo.findAll().size());
        userRepo.deleteByEmail("tobi75500@gmail.com");
        assertEquals(0, userRepo.findAll().size());
    }
}