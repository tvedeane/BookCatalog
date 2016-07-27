package com.bookcatalog.model;

import javax.persistence.*;

@Entity
public class Filename {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long filename_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public Long getFilename_id() {
        return filename_id;
    }

    public void setFilename_id(Long filename_id) {
        this.filename_id = filename_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
