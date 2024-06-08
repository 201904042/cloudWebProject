package com.example.config;

import org.springframework.boot.context.properties.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "bookDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.book")
    public DataSource bookDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "reviewDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.review")
    public DataSource reviewDataSource() {
        return DataSourceBuilder.create().build();
    }
}
