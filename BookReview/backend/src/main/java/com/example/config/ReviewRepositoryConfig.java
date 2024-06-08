package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.reviewrepository",
        entityManagerFactoryRef = "reviewEntityManagerFactory",
        transactionManagerRef = "reviewTransactionManager"
)
public class ReviewRepositoryConfig {
}
