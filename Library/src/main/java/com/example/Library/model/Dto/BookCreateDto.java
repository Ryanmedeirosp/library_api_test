package com.example.Library.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCreateDto {
    
    String title;
    String author;
    String isbn;
    Integer yearOfPublication;
}
