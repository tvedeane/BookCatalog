package com.bookcatalog.repository;

import com.bookcatalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
}
