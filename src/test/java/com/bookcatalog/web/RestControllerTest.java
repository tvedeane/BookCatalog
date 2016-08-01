package com.bookcatalog.web;

import com.bookcatalog.BookCatalogApp;
import com.bookcatalog.model.Author;
import com.bookcatalog.model.Book;
import com.bookcatalog.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringApplicationConfiguration(BookCatalogApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class RestControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter =
                Arrays.stream(converters)
                      .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

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

    private String json(Book o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}