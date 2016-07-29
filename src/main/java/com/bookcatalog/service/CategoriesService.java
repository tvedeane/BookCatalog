package com.bookcatalog.service;

import com.bookcatalog.model.Category;

import java.util.List;

public interface CategoriesService {
    List<Category> findAll();
}
