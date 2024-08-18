package ru.shop.PrimeCloud.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

public class EmptyEmbeddedJdbcConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmptyEmbeddedJdbcConfig.class);

    @Bean
    public DataSource dataSource() {
        ;
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2).setName("primecloud").build();
        } catch (Exception e){
            LOGGER.error("Empty Embedded DataSource bean cannot be crated");
            return null;
        }
    }
}
