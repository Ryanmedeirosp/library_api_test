package com.example.Library.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "register_date")
    LocalDate registerDate;

    @Column(name = "single_card")
    UUID singleCard;

    @Column(name = "status")
    String status;

    //Relação Um para Muitos entre Usuário e Empréstimo 
    @OneToMany(mappedBy = "user")
    List<Loan> loans;

    public User(String name, String email, LocalDate registerDate, String status) {
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
        this.status = status;
    } 
    
}
