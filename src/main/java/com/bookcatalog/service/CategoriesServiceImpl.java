package com.bookcatalog.service;

import com.bookcatalog.model.Category;
import com.bookcatalog.repository.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll();
    }
}
