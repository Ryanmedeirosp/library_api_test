package com.example.Library.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "author")
    public String author;


    @Column(name = "isbn")
    public String isbn;

    @Column(name= "status")
    public String status;

    @Column(name = "year_of_publication")
    public Integer yearOfPublication;

    @ManyToMany(mappedBy = "books")  // Relacionamento bidirecional, 'books' é o nome do campo em Loan
    @JsonIgnore
    List<Loan> loans;

    public Book(String title, String author, String isbn, Integer yearOfPublication, String status) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfPublication = yearOfPublication;
        this.status = status;
    }

}
