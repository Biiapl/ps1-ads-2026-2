package br.edu.fatecfranca.api.dtos;


import java.time.LocalDate;


public record CustomerRequest(
   String name,
   String identDocument,
   LocalDate birthDate,
   String streetName,
   String houseNumber,
   String complements,
   String district,
   String municipality,
   String state,
   String phone,
   String email
) {}
