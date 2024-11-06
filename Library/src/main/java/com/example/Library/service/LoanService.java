package com.example.Library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Library.model.Loan;
import com.example.Library.repository.LoanRepository;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoan(){
        return loanRepository.findAll();
    }

    public Loan getLoanById(Integer id){
        return loanRepository.findById(id).orElse(null);
    }

    public void createLoan(Loan loan){
        loanRepository.save(loan);
    }

    public void deleteLoan(Integer id){
        loanRepository.deleteById(id);
    }
}
