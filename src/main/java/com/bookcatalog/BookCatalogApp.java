package com.bookcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bookcatalog.repository")
@EntityScan(basePackages = "com.bookcatalog.model")
public class BookCatalogApp {
    public static void main(String[] args) {
        SpringApplication.run(BookCatalogApp.class, args);
    }
}
