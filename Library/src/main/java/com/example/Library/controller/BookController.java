package com.example.Library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Library.model.Book;
import com.example.Library.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {
    
    private final BookService bookService;

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id){
        return bookService.getBookById(id);
    }

    //http://localhost:8080/books/author?author={AUTHOR}

    @GetMapping("/author")
    public List<Book> getBookByAuthor(@RequestParam("author") String author){
        return bookService.getBookByAuthor(author);
    }

    //http://localhost:8080/books/year?yearOfPublication={YEAR}

    @GetMapping("/year")
    public List<Book> getBookByYearOfPublication(@RequestParam("yearOfPublication") Integer yearOfPublication){
        return bookService.getBookByYearOfPublication(yearOfPublication);
    }

    @PostMapping()
    public void addBook(@RequestBody Book book) {
         bookService.createBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }
}
