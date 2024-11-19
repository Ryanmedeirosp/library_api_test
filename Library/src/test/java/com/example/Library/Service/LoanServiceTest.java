package com.example.Library.Service;

import com.example.Library.model.Book;
import com.example.Library.model.Dto.LoanCreateDto;
import com.example.Library.model.Enums.StatusEnum;
import com.example.Library.model.Loan;
import com.example.Library.model.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.LoanRepository;
import com.example.Library.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.Library.service.LoanService;

public class LoanServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

    private LoanService loanService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loanService = new LoanService(bookRepository, userRepository, loanRepository);
    }

    @Test
    void testGetAllLoans() {
        // Arrange
        Loan loan1 = new Loan();
        loan1.setId(1);
        loan1.setStatus(StatusEnum.EM_ANDAMENTO);
        loan1.setStartDate(LocalDate.now());
        loan1.setDevolutionDate(LocalDate.now().plusDays(7));

        Loan loan2 = new Loan();
        loan2.setId(2);
        loan2.setStatus(StatusEnum.CONCLUIDO);
        loan2.setStartDate(LocalDate.now().minusDays(10));
        loan2.setDevolutionDate(LocalDate.now().minusDays(3));

        List<Loan> loans = Arrays.asList(loan1, loan2);

        when(loanRepository.findAll()).thenReturn(loans);

        // Act
        List<Loan> result = loanService.getAllLoan();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(StatusEnum.PENDENTE, result.get(1).getStatus());  // The second loan should be updated to "PENDENTE" because it's overdue
        verify(loanRepository, times(1)).findAll();
    }

    @Test
    void testCreateLoan() {
        // Arrange
         LoanCreateDto loanCreateDto = new LoanCreateDto("user@example.com", Arrays.asList(1, 2));

        User user = new User();
        user.setEmail("user@example.com");

        Book book1 = new Book();
        book1.setId(1);
        book1.setStatus(true);  // Book available

        Book book2 = new Book();
        book2.setId(2);
        book2.setStatus(true);  // Book available

        when(userRepository.findByEmail("user@example.com")).thenReturn(java.util.Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book1));
        when(bookRepository.findById(2)).thenReturn(java.util.Optional.of(book2));
        when(bookRepository.saveAll(Arrays.asList(book1, book2))).thenReturn(Arrays.asList(book1, book2));

        // Act
        loanService.createLoan(loanCreateDto);

        // Assert
        verify(loanRepository, times(1)).save(any(Loan.class));  // Verify if the loan was saved
        verify(bookRepository, times(2)).save(any(Book.class)); // Verify if the books' status was updated
    }

    @Test
    void testCreateLoan_BookAlreadyLoaned() {
        // Arrange
        LoanCreateDto loanCreateDto = new LoanCreateDto("user@example.com", Arrays.asList(1));

        User user = new User();
        user.setEmail("user@example.com");

        Book book1 = new Book();
        book1.setId(1);
        book1.setStatus(false);  // Book is already loaned

        when(userRepository.findByEmail("user@example.com")).thenReturn(java.util.Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book1));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> loanService.createLoan(loanCreateDto));
        assertEquals("O livro já está emprestado.", exception.getMessage());  // Check the error message
    }

    @Test
    void testDeleteLoan() {
        // Arrange
        Integer loanId = 1;

        // Act
        loanService.deleteLoan(loanId);

        // Assert
        verify(loanRepository, times(1)).deleteById(loanId);  // Verifies if deleteById was called once
    }

    @Test
    void testUpdateLoanStatus() {
        // Arrange
        Loan loan = new Loan();
        loan.setId(1);
        loan.setStatus(StatusEnum.EM_ANDAMENTO);

        Book book1 = new Book();
        book1.setId(1);
        book1.setStatus(false);  // Book was loaned out

        User user = new User();
        user.setEmail("user@example.com");

        LoanCreateDto loanCreateDto = new LoanCreateDto("user@example.com", Arrays.asList(1));

        when(loanRepository.findById(1)).thenReturn(java.util.Optional.of(loan));
        when(userRepository.findByEmail("user@example.com")).thenReturn(java.util.Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book1));
        when(bookRepository.save(any(Book.class))).thenReturn(book1);

        // Act
        loanService.updateTable(1, loanCreateDto);

        // Assert
        verify(loanRepository, times(1)).save(any(Loan.class));  // Verify if the loan status was updated
    }

    @Test
    void testUpdateLoanStatus_BookNotLoaned() {
        // Arrange
        Loan loan = new Loan();
        loan.setId(1);
        loan.setStatus(StatusEnum.EM_ANDAMENTO);

        Book book1 = new Book();
        book1.setId(1);
        book1.setStatus(true);  // Book is not loaned

        User user = new User();
        user.setEmail("user@example.com");

        LoanCreateDto loanCreateDto = new LoanCreateDto("user@example.com", Arrays.asList(1));
        when(loanRepository.findById(1)).thenReturn(java.util.Optional.of(loan));
        when(userRepository.findByEmail("user@example.com")).thenReturn(java.util.Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book1));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> loanService.updateTable(1, loanCreateDto));
        assertEquals("O livro não está emprestado", exception.getMessage());  // Check the error message
    }
}
