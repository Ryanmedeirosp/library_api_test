package com.example.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Library.model.Loan;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

} 
