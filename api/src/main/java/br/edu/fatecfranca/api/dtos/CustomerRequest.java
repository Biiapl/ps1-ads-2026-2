
package br.edu.fatecfranca.api.dtos;


import java.time.LocalDate;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;


public record CustomerRequest(
  
   @NotBlank(message = "O nome é obrigatório.")
   String name,


   @NotBlank(message = "O documento de identificação é obrigatório.")
   String identDocument,


   @PastOrPresent(
       message = "A data de nascimento não pode estar no futuro."
   )
   LocalDate birthDate,


   @NotBlank(message = "O logradouro é obrigatório.")
   String streetName,


   @NotBlank(message = "O número do imóvel é obrigatório.")
   String houseNumber,


   String complements,


   @NotBlank(message = "O bairro é obrigatório.")
   String district,


   @NotBlank(message = "O município é obrigatório.")
   String municipality,


   @NotBlank(message = "A UF é obrigatória.")
   @Pattern(
       regexp = "[A-Z]{2}",
       message = "A UF deve conter duas letras maiúsculas."
   )
   String state,


   @NotBlank(message = "O telefone é obrigatório.")
   String phone,


   @NotBlank(message = "O e-mail é obrigatório.")
   @Email(message = "O e-mail deve ter um formato válido.")
   String email


) {}

