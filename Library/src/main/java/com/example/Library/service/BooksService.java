package com.example.Library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.Books;
import com.example.Library.repository.BooksRepository;

@Service
public class BooksService {
    private final  BooksRepository booksRepository;

    public  BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    public Books getBookById(Integer id) {
        return booksRepository.findById(id).orElse(null);
    }


    public void createBook(Books book){
        booksRepository.save(book);
    }

    public void  deleteBook(Integer id){
        booksRepository.deleteById(id);
    }


}
