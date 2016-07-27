package com.bookcatalog.repository;

import com.bookcatalog.model.Filename;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilenameJpaRepository extends JpaRepository<Filename, Long> {
}
