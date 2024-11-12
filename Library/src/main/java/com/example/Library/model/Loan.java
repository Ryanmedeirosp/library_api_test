package com.example.Library.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "devolution_date")
    LocalDate devolutionDate;

    @Column(name = "status")
    String status;

    @ManyToMany
    @JoinTable(
        name = "loan_book", 
        joinColumns = @JoinColumn(name = "book_id"), 
        inverseJoinColumns = @JoinColumn(name = "loan_id"))
    List<Book> books;

    @ManyToOne()
    @JoinColumn(name="id_user", nullable=false)
    User user;

    public Loan(LocalDate startDate, LocalDate devolutionDate, String status) {
        this.startDate = startDate;
        this.devolutionDate = devolutionDate;
        this.status = status;
    }
}
