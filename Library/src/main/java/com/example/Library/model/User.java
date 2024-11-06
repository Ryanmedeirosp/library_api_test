package com.example.Library.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String name;

    String email;

    LocalDate registerDate;

    Integer singleCard;

    Boolean status;

    public User(String name, String email, LocalDate registerDate, Integer singleCard, Boolean status) {
        this.name = name;
        this.email = email;
        this.registerDate = registerDate;
        this.singleCard = singleCard;
        this.status = status;
    } 
}