package com.bookcatalog.repository;

import com.bookcatalog.BookCatalogApp;
import com.bookcatalog.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringApplicationConfiguration(BookCatalogApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryJpaRepositoryTest {
    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Test
    public void testFindAllCategories() {
        List<Category> categories = categoryJpaRepository.findAll();

        assertEquals(3, categories.size());
    }
}