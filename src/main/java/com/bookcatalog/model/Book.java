package com.bookcatalog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "book_id")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long book_id;

    private String isbn;
    private String title;
    private byte[] picture;

    public Book() {}

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private List<Filename> filenames;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BOOK_CATEGORY",
               joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BOOK_AUTHOR",
               joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id"))
    private List<Author> authors;
}
