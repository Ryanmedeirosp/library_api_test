package com.example.Library.Service;

import com.example.Library.model.Dto.LoanCreateDto;
import com.example.Library.model.Book;
import com.example.Library.model.Loan;
import com.example.Library.model.User;
import com.example.Library.model.Enums.StatusEnum;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.LoanRepository;
import com.example.Library.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.Library.service.LoanService;

public class LoanServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private User user;
    private Book book;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração do usuário mockado
        user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setEmail("johndoe@example.com");

        // Configuração do livro mockado
        book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setStatus(true);
    }

    @Test
    public void testCreateLoan() {
        // Criando LoanCreateDto diretamente no teste
        LoanCreateDto loanCreateDto = new LoanCreateDto("johndoe@example.com", Arrays.asList(1));

        // Simulando que o usuário existe no repositório
        when(userRepository.findByEmail(loanCreateDto.getEmail())).thenReturn(java.util.Optional.of(user));

        // Simulando que o livro existe e está disponível
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));

        // Simulando a criação do empréstimo
        Loan loan = new Loan();
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // Chama o método de criar empréstimo
        loanService.createLoan(loanCreateDto);

        // Verificar se o repositório salva o empréstimo corretamente
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    public void testCreateLoanWithUnavailableBook() {
        // Criando LoanCreateDto diretamente no teste
        LoanCreateDto loanCreateDto = new LoanCreateDto("johndoe@example.com", Arrays.asList(1));

        // Simulando que o usuário existe no repositório
        when(userRepository.findByEmail(loanCreateDto.getEmail())).thenReturn(java.util.Optional.of(user));

        // Simulando que o livro já está emprestado (status = false)
        book.setStatus(false);
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));

        // Espera-se que lance uma exceção devido ao livro estar emprestado
        assertThrows(RuntimeException.class, () -> loanService.createLoan(loanCreateDto));

        // Verificar que o método save não foi chamado
        verify(loanRepository, times(0)).save(any(Loan.class));
    }

    @Test
    public void testGetAllLoans() {
        // Criando empréstimos mockados
        List<Loan> loans = new ArrayList<>();
        Loan loan = new Loan();
        loan.setStartDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(7));
        loan.setStatus(StatusEnum.EM_ANDAMENTO);
        loan.setUser(user);
        loan.setBooks(Arrays.asList(book));
        loans.add(loan);

        when(loanRepository.findAll()).thenReturn(loans);

        List<Loan> result = loanService.getAllLoan();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(StatusEnum.EM_ANDAMENTO, result.get(0).getStatus());
    }

    @Test
    public void testUpdateLoanStatus() {
        // Criando LoanCreateDto diretamente no teste
        LoanCreateDto loanCreateDto = new LoanCreateDto("johndoe@example.com", Arrays.asList(1));

        // Simulando que o usuário existe no repositório
        when(userRepository.findByEmail(loanCreateDto.getEmail())).thenReturn(java.util.Optional.of(user));

        // Simulando que o livro existe e está disponível
        when(bookRepository.findById(1)).thenReturn(java.util.Optional.of(book));

        // Criar e salvar o empréstimo
        loanService.createLoan(loanCreateDto);

        // Simulando que o empréstimo foi salvo e que a data de devolução passou
        Loan loan = loanRepository.findById(1).orElseThrow();
        loan.setDevolutionDate(LocalDate.now().minusDays(1));  // Configurar como se já tivesse passado a data de devolução

        // Atualiza o status do empréstimo
        loanService.updateStatus(Arrays.asList(loan));

        // Verificar se o status do empréstimo foi alterado para PENDENTE
        assertEquals(StatusEnum.PENDENTE, loan.getStatus());
    }
}
