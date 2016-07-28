package com.bookcatalog.web;

import com.bookcatalog.model.Book;
import com.bookcatalog.service.BooksService;
import com.bookcatalog.service.FilenamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RestController {

    @Autowired
    private BooksService booksService;
    @Autowired
    private FilenamesService filenamesService;

    @RequestMapping("/books")
    @ResponseBody
    public List<Book> getBooksSummary() {
        return null;
    }
}
