package com.bookcatalog.web;

import com.bookcatalog.model.Book;
import com.bookcatalog.service.BooksService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WebControllerTest extends AbstractJsonTest {
    @Autowired
    WebController webController;

    @Mock
    BooksService booksService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        webController.setBooksService(booksService);
    }

    @Test
    public void uploadPicture() throws Exception {
        Long book_id = 1L;
        when(booksService.findOne(book_id)).thenReturn(new Book());
        when(booksService.saveBook(any())).thenReturn(new Book());

        this.mockMvc.perform(fileUpload("/picture")
                    .file(new MockMultipartFile("picture", "abc.png", MediaType.IMAGE_PNG_VALUE, new byte[]{0x00}))
                    .param("book_id", String.valueOf(book_id)))
                .andExpect(status().isOk());
    }
}
