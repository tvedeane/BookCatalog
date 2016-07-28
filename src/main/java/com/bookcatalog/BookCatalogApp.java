package com.bookcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.bookcatalog.model")
public class BookCatalogApp {
    public static void main(String[] args) {
        SpringApplication.run(BookCatalogApp.class, args);
    }
}
