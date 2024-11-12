package com.example.Library.model.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanCreateDto {

    // LocalDate startDate;

    // LocalDate devolutionDate;

    // String status;

    String email;

    List<String> bookCodes;
}
   
