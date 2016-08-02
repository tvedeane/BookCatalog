package com.bookcatalog.service;

import com.bookcatalog.model.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface BooksService {
    List<Book> findAll();

    Book findOne(Long book_id);

    List<Book> findByTitleLike(String bookTitle);

    List<Book> findByCategoryNameLike(String categoryName);

    List<Book> findByFilenameLike(String filename);

    Book saveBook(Book book);

    void deleteBook(Long id) throws DataAccessException;
}
