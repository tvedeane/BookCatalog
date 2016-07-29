package com.bookcatalog.service;

import com.bookcatalog.model.Filename;
import com.bookcatalog.repository.FilenameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilenamesServiceImpl implements FilenamesService {
    @Autowired
    FilenameJpaRepository filenameJpaRepository;

    @Override
    public List<Filename> findAll() {
        return filenameJpaRepository.findAll();
    }
}
