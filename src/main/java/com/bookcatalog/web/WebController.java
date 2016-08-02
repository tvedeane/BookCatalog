package com.bookcatalog.web;

import com.bookcatalog.model.Book;
import com.bookcatalog.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class WebController {
    @Autowired
    private BooksService booksService;

    @RequestMapping("/login")
    String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

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

    @RequestMapping(value = "/edit", method = GET)
    String editBookDetails(Model model, @RequestParam(value = "book_id") Long book_id) {
        model.addAttribute("book", booksService.findOne(book_id));
        return "edit";
    }

    @RequestMapping(value = "/edit", method = POST)
    @Transactional
    String postBookDetails(Model model, @ModelAttribute Book newBook) {
        final Book book = booksService.findOne(newBook.getBook_id());
        book.setTitle(newBook.getTitle());
        book.setIsbn(newBook.getIsbn());
        model.addAttribute("book", booksService.saveBook(book));
        return "book";
    }

    @RequestMapping(value = "/picture", method = GET)
    ResponseEntity<byte[]> getPicture(@RequestParam(value = "book_id") Long book_id) {
        byte[] imageContent = booksService.findOne(book_id).getPicture();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "/picture", method = POST)
    @Transactional
    String postPicture(
            Model model, @RequestParam("picture") MultipartFile picture, @RequestParam("book_id") String book_id) {
        final Book book = booksService.findOne(Long.valueOf(book_id));
        try {
            book.setPicture(picture.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("book", booksService.saveBook(book));
        return "book";
    }

    void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }
}
