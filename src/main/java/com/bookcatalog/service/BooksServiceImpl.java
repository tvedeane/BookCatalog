package com.bookcatalog.service;

import com.bookcatalog.model.Book;
import com.bookcatalog.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksServiceImpl implements BooksService {
    @Autowired
    BookJpaRepository bookJpaRepository;

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Book findOne(Long book_id) {
        return bookJpaRepository.findOne(book_id);
    }
}
