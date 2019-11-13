package com.example.demo;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourcesConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSourceOne() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.liquibase")
    public LiquibaseProperties liquibaseOneProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.liquibase.configuration")
    public SpringLiquibase liqubaseOne() {
        return springLiquibase(dataSourceOne(), liquibaseOneProperties());
    }

    @Bean
    @ConfigurationProperties("spring.secondary.datasource")
    public DataSource dataSourceTwo() {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean
    @ConfigurationProperties("spring.secondary.liquibase")
    public LiquibaseProperties liquibaseTwoProperties() {
        return new LiquibaseProperties();
    }

    @Bean
    @ConfigurationProperties("spring.secondary.liquibase.configuration")
    public SpringLiquibase liqubaseTwo() {
        return springLiquibase(dataSourceTwo(), liquibaseTwoProperties());
    }

    private static SpringLiquibase springLiquibase(DataSource dataSource, LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
