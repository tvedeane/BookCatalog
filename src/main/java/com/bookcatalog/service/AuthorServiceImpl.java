package com.bookcatalog.service;

import com.bookcatalog.model.Author;
import com.bookcatalog.repository.AuthorJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorsService {
    @Autowired
    AuthorJpaRepository authorJpaRepository;

    @Override
    public List<Author> findAll() {
        return authorJpaRepository.findAll();
    }
}
