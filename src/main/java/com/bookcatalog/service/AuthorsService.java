package com.bookcatalog.service;

import com.bookcatalog.model.Author;

import java.util.List;

public interface AuthorsService {
    List<Author> findAll();
}
