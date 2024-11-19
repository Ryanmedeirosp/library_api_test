package com.example.Library.Service;

import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Library.service.BookService;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");

        List<Book> books = Arrays.asList(book1, book2);

        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookService.getAllBooks();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals("Book 2", result.get(1).getTitle());

        // Verificar se o método findAll foi chamado
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetAllBooksByTitleAsc() {
        // Arrange
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setAuthor("Author 1");

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setAuthor("Author 2");

        List<Book> books = Arrays.asList(book2, book1);

        when(bookRepository.findAll(Sort.by(Sort.Direction.ASC, "title"))).thenReturn(books);

        // Act
        List<Book> result = bookService.getAllBooksByTitleAsc();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Book 1", result.get(0).getTitle());
        assertEquals("Book 2", result.get(1).getTitle());

        // Verificar se o método findAll com ordenação foi chamado
        verify(bookRepository, times(1)).findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    @Test
    void testGetBookById() {
        // Arrange
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book 1");
        book.setAuthor("Author 1");

        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));

        // Act
        Book result = bookService.getBookById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Book 1", result.getTitle());

        // Verificar se o método findById foi chamado
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void testCreateBook() {
        // Arrange
        Book book = new Book();
        book.setTitle("Book 1");
        book.setAuthor("Author 1");
        book.setIsbn("12345");

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        bookService.createBook(book);

        // Assert
        verify(bookRepository, times(1)).save(any(Book.class)); // Verifica se o método save foi chamado
    }

    @Test
    void testDeleteBook() {
        // Arrange
        Integer bookId = 1;

        // Act
        bookService.deleteBook(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId); // Verifica se o método deleteById foi chamado
    }
}
