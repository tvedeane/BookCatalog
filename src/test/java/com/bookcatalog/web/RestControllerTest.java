package com.bookcatalog.web;

import com.bookcatalog.model.Author;
import com.bookcatalog.model.Book;
import com.bookcatalog.model.Category;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestControllerTest extends AbstractJsonTest {
    @Test
    public void getAllBooks() throws Exception {
        this.mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllAuthors() throws Exception {
        this.mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].name").value("Bruce Eckel"));
    }

    @Test
    public void getAllCategories() throws Exception {
        this.mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].name").value("Software"));
    }

    @Test
    public void getAllFilenames() throws Exception {
        this.mockMvc.perform(get("/api/filenames"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].name").value("ThinkingInJava4thEd.pdf"));
    }

    @Test
    public void getAllBooksWithTitleLike() throws Exception {
        this.mockMvc.perform(get("/api/book/title/Java"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllBooksWithCategoryNameLike() throws Exception {
        this.mockMvc.perform(get("/api/book/category/Language"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void getAllBooksWithFilenameLike() throws Exception {
        this.mockMvc.perform(get("/api/book/filename/pdf"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].title").value("Thinking in Java (4th Edition)"));
    }

    @Test
    public void addNewBook() throws Exception {
        String title = "Superintelligence: Paths, Dangers, Strategies";
        String authorName = "Nick Bostrom";
        String categoryName = "Computer Science";
        Book newBook = new Book("9780198739838", title);
        newBook.setAuthors(Collections.singletonList(new Author(authorName)));
        newBook.setCategories(Collections.singletonList(new Category(categoryName)));
        this.mockMvc.perform(post("/api/book")
                .content(this.json(newBook))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.authors[0].name").value(authorName))
                .andExpect(jsonPath("$.categories[0].name").value(categoryName));

        this.mockMvc.perform(get("/api/book/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.title").value(title));
    }
}