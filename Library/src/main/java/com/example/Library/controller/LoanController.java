package com.example.Library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Library.model.Dto.LoanCreateDto;
import com.example.Library.model.Loan;
import com.example.Library.service.LoanService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/loans")
public class LoanController {
    
    private final LoanService loanService;

    @GetMapping()
    public List<Loan> getAllLoans() {
        return loanService.getAllLoan();
    }

    // @GetMapping("/delay")
    // public List<Loan> getUserByLoanInDelay() {
    //     return loanService.getUserByLoanInDelay();
    // }

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Integer id){
        return loanService.getLoanById(id);
    }

    @PostMapping()
    public void addLoan(@RequestBody LoanCreateDto loan) {
         loanService.createLoan(loan);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Integer id) {
        loanService.deleteLoan(id);
    }

    //http://localhost:8080/loans/updatetest
    // @GetMapping("/updatetest")
    // public void updateTable(){
    //     loanService.updateTable();
    // }
}
