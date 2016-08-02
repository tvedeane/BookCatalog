package com.bookcatalog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long category_id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}
