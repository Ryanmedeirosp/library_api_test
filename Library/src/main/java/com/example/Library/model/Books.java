package com.example.Library.model;

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
public class Books {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String title;

    String author;

    Integer isbn;

    String yearOfPublication;

    public Books(String title, String author, Integer isbn, String yearOfPublication) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
    }
}
