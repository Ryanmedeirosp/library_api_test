package com.example.Library.Service;

import java.util.List;

import com.example.Library.model.Dto.BookCreateDto;
import com.example.Library.model.Book;
import com.example.Library.repository.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Library.service.BookService;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração do livro mockado
        book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setYearOfPublication(2023);
        book.setStatus(true);
    }

    @Test
    public void testCreateBook() {
        // Criando BookCreateDto diretamente no teste
        BookCreateDto bookCreateDto = new BookCreateDto("Test Book", "Test Author", "1234567890", 2023);

        // Simulando que o livro será salvo no repositório
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        bookService.createBook(bookCreateDto);

        // Verificar se o repositório salva o livro corretamente
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    public void testCreateBookWithInvalidData() {
        // Criando BookCreateDto com dados inválidos diretamente no teste
        BookCreateDto bookCreateDto = new BookCreateDto(null, "Test Author", "1234567890", 2023);

        // Espera-se que lance uma IllegalArgumentException devido ao título ser nulo
        assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookCreateDto));

        // Verificar que o método save não foi chamado
        verify(bookRepository, times(0)).save(any(Book.class));
    }

    @Test
    public void testGetAllBooks() {
        // Criando livros mockados
        when(bookRepository.findAll()).thenReturn(List.of(book));

        var result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));

        Book result = bookService.getBookById(1);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.empty());

        Book result = bookService.getBookById(1);

        assertNull(result);
    }
}
