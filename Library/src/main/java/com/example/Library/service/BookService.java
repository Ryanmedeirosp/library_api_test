package com.example.Library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Library.model.Book;
import com.example.Library.model.Dto.BookCreateDto;
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

        if (id == null || id == 0) throw new IllegalArgumentException(); 

        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBookByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByYearOfPublication(Integer yearOfPublication){

        if (yearOfPublication == null || yearOfPublication == 0) throw new IllegalArgumentException(); 

        return bookRepository.findByYearOfPublication(yearOfPublication);
    }

    public void createBook(BookCreateDto request){

        if (bookVerification(request)) {

            Book book = new Book();

            book.setTitle(request.getTitle());
            book.setAuthor(request.getAuthor());
            book.setIsbn(request.getIsbn());
            book.setYearOfPublication(request.getYearOfPublication());
            
            book.setStatus(true);
            book.setLoans(new ArrayList<>());
    
            bookRepository.save(book);
            
        }
    }

    public void deleteBook(Integer id){

        if (id == null || id == 0) throw new IllegalArgumentException(); 
        
        bookRepository.deleteById(id);
    }

    public Boolean bookVerification(BookCreateDto request){

        if(request.getTitle() == null || request.getTitle().isBlank()) throw new IllegalArgumentException();
        if(request.getAuthor() == null || request.getAuthor().isBlank()) throw new IllegalArgumentException();
        if(request.getIsbn() == null || request.getIsbn().isBlank()) throw new IllegalArgumentException();
        if(request.getYearOfPublication() == null || request.getYearOfPublication() < 0) throw new IllegalArgumentException();

        return true;
    }
}
