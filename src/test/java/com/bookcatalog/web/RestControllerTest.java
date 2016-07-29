package com.bookcatalog.web;

import com.bookcatalog.BookCatalogApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringApplicationConfiguration(BookCatalogApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RestControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAllBooks() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllAuthors() throws Exception {
        this.mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("Bruce Eckel"));
    }

    @Test
    public void getAllCategories() throws Exception {
        this.mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("Software"));
    }

    @Test
    public void getAllFilenames() throws Exception {
        this.mockMvc.perform(get("/api/filenames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].name").value("ThinkingInJava4thEd.pdf"));
    }

    @Test
    public void getAllBooksWithTitleLike() throws Exception {
        this.mockMvc.perform(get("/api/book/title/Java"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllBooksWithCategoryNameLike() throws Exception {
        this.mockMvc.perform(get("/api/book/category/Language"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllBooksWithFilenameLike() throws Exception {
        this.mockMvc.perform(get("/api/book/filename/pdf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }
}