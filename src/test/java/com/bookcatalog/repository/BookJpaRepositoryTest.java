package com.bookcatalog.repository;

import com.bookcatalog.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations={"classpath:com/bookcatalog/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

//    @PersistenceContext
//    private EntityManager entityManager;

    @Test
    public void testAnything() {
        Book one = bookJpaRepository.findOne(1L);
        assertNotNull(one);
    }
}