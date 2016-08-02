package com.bookcatalog.web;

import com.bookcatalog.model.Author;
import com.bookcatalog.model.Book;
import com.bookcatalog.model.Category;
import com.bookcatalog.model.Filename;
import com.bookcatalog.service.AuthorsService;
import com.bookcatalog.service.BooksService;
import com.bookcatalog.service.CategoriesService;
import com.bookcatalog.service.FilenamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/api")
public class RestController {

    @Autowired
    private BooksService booksService;
    @Autowired
    private AuthorsService authorsService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private FilenamesService filenamesService;

    @RequestMapping("/books")
    @ResponseBody
    public List<Book> getAllBooks() {
        return booksService.findAll();
    }

    @RequestMapping("/authors")
    @ResponseBody
    public List<Author> getAllAuthors() {
        return authorsService.findAll();
    }

    @RequestMapping("/categories")
    @ResponseBody
    public List<Category> getAllCategories() {
        return categoriesService.findAll();
    }

    @RequestMapping("/filenames")
    @ResponseBody
    public List<Filename> getAllFilenames() {
        return filenamesService.findAll();
    }

    @RequestMapping("/book/{id}")
    @ResponseBody
    public Book getBookById(@PathVariable String id) {
        return booksService.findOne(Long.valueOf(id));
    }

    @RequestMapping(value = "/book", method = POST, consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        final Book newBook = booksService.saveBook(book);
        final HttpStatus responseCode =
                newBook != null && newBook.getBook_id() != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(responseCode).body(newBook);
    }

    @RequestMapping(value = "/book", method = PUT, consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<String> editBook(@RequestBody Book book) {
        if (book.getBook_id() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing ID for book entity.");

        try {
            booksService.saveBook(book);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @RequestMapping("/book/title/{title}")
    @ResponseBody
    public List<Book> findBooksWithTitleLike(@PathVariable String title) {
        return booksService.findByTitleLike(title);
    }

    @RequestMapping("/book/category/{name}")
    @ResponseBody
    public List<Book> findBooksWithCategoryNameLike(@PathVariable String name) {
        return booksService.findByCategoryNameLike(name);
    }

    @RequestMapping("/book/filename/{name}")
    @ResponseBody
    public List<Book> findBooksWithFilenameLike(@PathVariable String name) {
        return booksService.findByFilenameLike(name);
    }
}
