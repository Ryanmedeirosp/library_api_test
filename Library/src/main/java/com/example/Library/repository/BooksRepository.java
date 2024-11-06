package com.example.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.model.Books;


@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    
} 
