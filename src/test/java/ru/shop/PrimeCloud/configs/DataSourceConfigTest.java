package ru.shop.PrimeCloud.configs;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceConfigTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfigTest.class);

    @Test
    public void testSimpleDataSource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
        testDataSource(dataSource);
    }

    private void testDataSource(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int mockVal = rs.getInt("1");
                assertEquals(1, mockVal);
            }
        } catch (SQLException e) {
            LOGGER.error("Something unexpected happened.", e);
        }
    }

}