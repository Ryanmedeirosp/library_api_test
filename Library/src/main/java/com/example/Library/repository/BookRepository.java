package com.example.Library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    List<Book> findByYearOfPublication(Integer yearOfPublication);
    Optional<Book> findByIsbn(String isbn);
} 
