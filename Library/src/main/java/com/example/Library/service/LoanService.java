package com.example.Library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.Book;
import com.example.Library.model.Dto.LoanCreateDto;
import com.example.Library.model.Enums.StatusEnum;
import com.example.Library.model.Loan;
import com.example.Library.model.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.LoanRepository;
import com.example.Library.repository.UserRepository;

@Service
public class LoanService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public LoanService(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    //Buscar todos
    public List<Loan> getAllLoan(){

        List<Loan> loans = loanRepository.findAll();

        updateStatus(loans);
        for (Loan loan : loans) {
            
            if (LocalDate.now().isAfter(loan.devolutionDate)) {
                
                loan.setStatus(StatusEnum.PENDENTE);
            }
        };

        loanRepository.saveAll(loans);

        return loanRepository.findAll();
    }

    //Buscar por Status
    public List<Loan> getLoansByStatus(StatusEnum status){

        List<Loan> loans = loanRepository.findAll();

        updateStatus(loans);

        loanRepository.saveAll(loans);

        return loanRepository.findByStatus(status);
    }

    public Loan getLoanById(Integer id){
        return loanRepository.findById(id).orElse(null);
    }
    public void createLoan(LoanCreateDto request) {
        List<Book> books = new ArrayList<>();
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        for (Integer id : request.getBookCodes()) {
            Book book = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    
            if (book.getStatus().equals(false)) {
                throw new RuntimeException("O livro já está emprestado.");
            }
    
            book.setStatus(false);  // Atualize o status do livro
            books.add(book);
        }
    
        bookRepository.saveAll(books);  // Persistir os livros com status alterado
    
        Loan loan = new Loan();
        loan.setStartDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(7));
        loan.setStatus(StatusEnum.EM_ANDAMENTO);
        loan.setBooks(books);
        loan.setUser(user);
        loanRepository.save(loan);
    }

    public void deleteLoan(Integer id){
        loanRepository.deleteById(id);
    }

    public void updateTable(Integer id, LoanCreateDto request ){

        Loan loan = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        List<Book> books = new ArrayList<>();

        userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    

        for (Integer code : request.getBookCodes()) {

            Book book = bookRepository.findById(code)
                    .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    
            if (book.getStatus().equals(true)) {
                throw new RuntimeException("O livro não está emprestado");
            }
    
            book.setStatus(true);  // Atualize o status do livro

            books.add(book);
        }
        
        loan.setStatus(StatusEnum.CONCLUIDO);

        loanRepository.save(loan);
    
    }

    //Atualiza o Status do empréstimo para Atrasado
    public List<Loan> updateStatus(List<Loan> loans){

        loans = loanRepository.findAll();

        for (Loan loan : loans) {
            
            if (LocalDate.now().isAfter(loan.devolutionDate)) {
                
                loan.setStatus(StatusEnum.PENDENTE);
            }
        };

        return loans;
    }
}
