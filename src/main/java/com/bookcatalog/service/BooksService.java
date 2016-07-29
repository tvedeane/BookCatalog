package com.bookcatalog.service;

import com.bookcatalog.model.Book;

import java.util.List;

public interface BooksService {
    List<Book> findAll();

    Book findOne(Long book_id);
}
