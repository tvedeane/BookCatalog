package com.bookcatalog.repository;

import com.bookcatalog.model.Filename;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations={"classpath:com/bookcatalog/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FilenameJpaRepositoryTest {
    @Autowired
    private FilenameJpaRepository filenameJpaRepository;

    @Test
    public void testFindAllFilenames() {
        List<Filename> all = filenameJpaRepository.findAll();

        assertEquals(1, all.size());
    }
}