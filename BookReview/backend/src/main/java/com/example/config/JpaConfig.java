package com.example.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfig {
    @Bean(name = "bookEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean bookEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("bookDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.bookentity") // Book 엔티티 패키지 경로
                .persistenceUnit("book")
                .build();
    }

    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager bookTransactionManager(
            @Qualifier("bookEntityManagerFactory") EntityManagerFactory bookEntityManagerFactory) {
        return new JpaTransactionManager(bookEntityManagerFactory);
    }

    @Bean(name = "reviewEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean reviewEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("reviewDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.reviewentity") // Review 엔티티 패키지 경로
                .persistenceUnit("review")
                .build();
    }

    @Bean(name = "reviewTransactionManager")
    public PlatformTransactionManager reviewTransactionManager(
            @Qualifier("reviewEntityManagerFactory") EntityManagerFactory reviewEntityManagerFactory) {
        return new JpaTransactionManager(reviewEntityManagerFactory);
    }
}
