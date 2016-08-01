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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
