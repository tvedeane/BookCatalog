package com.bookcatalog.web;

import com.bookcatalog.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class WebController {
    @Autowired
    private BooksService booksService;

    @RequestMapping(value = {"/", "/index"})
    String index(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        model.addAttribute("books", booksService.findAll());
        return "index";
    }

    @RequestMapping("/book")
    String bookDetails(Model model, @RequestParam(value = "book_id") Long book_id) {
        model.addAttribute("book", booksService.findOne(book_id));
        return "book";
    }
}
