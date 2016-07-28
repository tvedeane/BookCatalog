package com.bookcatalog.service;

import com.bookcatalog.repository.FilenameJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilenamesServiceImpl implements FilenamesService {
    @Autowired
    FilenameJpaRepository filenameJpaRepository;
}
