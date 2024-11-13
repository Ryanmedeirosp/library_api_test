package com.example.Library.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.Book;
import com.example.Library.model.Dto.LoanCreateDto;
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

    public List<Loan> getAllLoan(){
        return loanRepository.findAll();
    }

    public Loan getLoanById(Integer id){
        return loanRepository.findById(id).orElse(null);
    }

    public void createLoan(LoanCreateDto request){
        List<Book> books = new ArrayList<>();
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("teste"));
        Book book = new Book();
        for (String isnb : request.getBookCodes()) {
            book = bookRepository.findByIsbn(isnb).orElseThrow(() -> new RuntimeException("teste"));
            books.add(book);
        }
        Loan loan = new Loan();
        loan.setStartDate(LocalDate.now());
        loan.setDevolutionDate(LocalDate.now().plusDays(7));
        loan.setStatus("Em andamento");
        loan.setBooks(books);
        loan.setUser(user);
        loanRepository.save(loan);
       
    }

    public void deleteLoan(Integer id){
        loanRepository.deleteById(id);
    }

    // public void updateTable(){

    //     Long tableCount = loanRepository.count();

    //     System.out.println(tableCount);
    // }
}
