package com.bookcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long book_id;

    private String isbn;
    private String title;
    private byte[] picture;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOK_ID")
    private List<Filename> filenames = new ArrayList<>();

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public List<Filename> getFilenames() {
        return filenames;
    }

    public void setFilenames(List<Filename> filenames) {
        this.filenames = filenames;
    }
}
