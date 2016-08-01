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

    @Override
    public List<Book> findByTitleLike(String bookTitle) {
        return bookJpaRepository.findByTitleLikeIgnoreCase(like(bookTitle));
    }

    @Override
    public List<Book> findByCategoryNameLike(String categoryName) {
        return bookJpaRepository.findByCategoriesNameLikeIgnoreCase(like(categoryName));
    }

    @Override
    public List<Book> findByFilenameLike(String filename) {
        return bookJpaRepository.findByFilenamesNameLikeIgnoreCase(like(filename));
    }

    @Override
    public Book saveBook(Book book) {
        return bookJpaRepository.saveAndFlush(book);
    }

    private String like(String categoryName) {
        return "%" + categoryName + "%";
    }
}
