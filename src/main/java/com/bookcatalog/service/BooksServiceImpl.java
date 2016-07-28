package com.bookcatalog.service;

import com.bookcatalog.repository.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksServiceImpl implements BooksService {
    @Autowired
    BookJpaRepository bookJpaRepository;
}
