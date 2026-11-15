
package br.edu.fatecfranca.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

    @NotBlank(message = "O nome completo é obrigatório.")
    String fullname,

    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Pattern(
        regexp = "^[A-Za-z0-9._-]+$",
        message = "O nome de usuário contém caracteres inválidos."
    )
    String username,

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ter um formato válido.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    String password,

    @NotNull(message = "É obrigatório informar se o usuário é administrador.")
    Boolean isAdmin

) {}
