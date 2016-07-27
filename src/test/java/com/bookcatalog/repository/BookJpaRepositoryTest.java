package com.bookcatalog.repository;

import com.bookcatalog.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations={"classpath:com/bookcatalog/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Test
    public void testFindAllBooks() {
        List<Book> books = bookJpaRepository.findAll();

        assertNotNull(books);
        assertEquals(3, books.size());
    }

    @Test
    @Transactional
    public void testBookWithFilename() {
        Book book = bookJpaRepository.findOne(1L);

        assertNotNull(book);
        assertEquals(1, book.getFilenames().size());
        assertEquals("ThinkingInJava4thEd.pdf", book.getFilenames().get(0).getName());
    }
}