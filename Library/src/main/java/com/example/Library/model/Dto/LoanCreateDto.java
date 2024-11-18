package com.example.Library.model.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanCreateDto {

    String email;

    List<Integer> bookCodes;
}
   
