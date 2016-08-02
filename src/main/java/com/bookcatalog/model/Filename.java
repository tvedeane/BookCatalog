package com.bookcatalog.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Filename {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long filename_id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;
}
