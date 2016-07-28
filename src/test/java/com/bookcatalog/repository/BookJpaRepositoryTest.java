package com.bookcatalog.repository;

import com.bookcatalog.BookCatalogApp;
import com.bookcatalog.model.Book;
import org.hibernate.LazyInitializationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@SpringApplicationConfiguration(BookCatalogApp.class)
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

    @Test
    @Transactional
    public void testBookWithCategories() {
        Book book = bookJpaRepository.findOne(1L);

        assertNotNull(book);
        assertEquals(3, book.getCategories().size());
    }

    @Test
    @Transactional
    public void testBookWithAuthors() {
        Book book = bookJpaRepository.findOne(1L);

        assertNotNull(book.getAuthors());
        assertEquals("Bruce Eckel", book.getAuthors().get(0).getName());
    }

    @Test
    public void testFindWithLike() {
        List<Book> books = bookJpaRepository.findByTitleLike("%Java%");

        assertEquals(3, books.size());
    }
}