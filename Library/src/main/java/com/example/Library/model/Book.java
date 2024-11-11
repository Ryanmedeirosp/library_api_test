package com.example.Library.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "isbn")
    String isbn;

    @Column(name = "year_of_publication")
    Integer yearOfPublication;

    public Book(String title, String author, String isbn, Integer yearOfPublication) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
    }

}
