package com.example.Library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

@Service
public class BookService {
    
    private final  BookRepository bookRepository;

    public  BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }


    public void createBook(Book book){
        bookRepository.save(book);
    }

    public void  deleteBook(Integer id){
        bookRepository.deleteById(id);
    }
}
