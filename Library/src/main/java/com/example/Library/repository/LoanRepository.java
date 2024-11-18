package com.example.Library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Library.model.Loan;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Query("SELECT l FROM Loan l WHERE l.status = 'Atraso'")
    List<Loan> findUserByLoanInDelay();
    List<Loan> findAllByfindAllByStatus(String status);
} 
