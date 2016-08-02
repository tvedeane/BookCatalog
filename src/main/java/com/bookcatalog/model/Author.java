package com.bookcatalog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long author_id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }
}
