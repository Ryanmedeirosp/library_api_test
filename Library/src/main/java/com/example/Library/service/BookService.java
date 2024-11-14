package com.example.Library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

@Service
public class BookService {
    
    private final  BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByTitleAsc(){

        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBookByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByYearOfPublication(Integer yearOfPublication){
        return bookRepository.findByYearOfPublication(yearOfPublication);
    }

    public void createBook(Book book){

        book.setLoans(new ArrayList<>());

        bookRepository.save(book);
    }

    public void deleteBook(Integer id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("zika"));
        bookRepository.deleteById(id);
    }

}
