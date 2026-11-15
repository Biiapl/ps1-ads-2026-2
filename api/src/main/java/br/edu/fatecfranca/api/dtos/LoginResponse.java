
package br.edu.fatecfranca.api.dtos;


public record LoginResponse(
   String token,
   UserResponse user
) {}


