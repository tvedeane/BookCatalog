package com.bookcatalog.repository;

import com.bookcatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleLikeIgnoreCase(String bookName);

    List<Book> findByCategoriesNameLikeIgnoreCase(String categoryName);

    List<Book> findByFilenamesNameLikeIgnoreCase(String filenameName);
}
