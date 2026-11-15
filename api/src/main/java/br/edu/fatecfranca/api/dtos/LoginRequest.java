package br.edu.fatecfranca.api.dtos;
import jakarta.validation.constraints.NotBlank;
public record LoginRequest(
@NotBlank String usernameOrEmail,
@NotBlank String password ) {}

