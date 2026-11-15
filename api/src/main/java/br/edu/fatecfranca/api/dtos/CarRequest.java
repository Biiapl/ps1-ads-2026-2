
package br.edu.fatecfranca.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CarRequest(

    @NotBlank(message = "A marca é obrigatória.")
    String brand,

    @NotBlank(message = "O modelo é obrigatório.")
    String model,

    @NotBlank(message = "A cor é obrigatória.")
    String color,

    @NotNull(message = "O ano de fabricação é obrigatório.")
    @Positive(message = "O ano de fabricação deve ser maior que zero.")
Integer yearManufacture,
    @NotNull(message = "É obrigatório informar se o veículo é importado.")
    Boolean imported,

    @NotBlank(message = "A placa é obrigatória.")
    @Pattern(
        regexp = "^(?:[A-Z]{3}-[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$",
        message = "A placa deve seguir o formato ABC-1234 ou ABC1D23."
    )
    String plates,

    @PastOrPresent(message = "A data de venda não pode estar no futuro.")
    LocalDate sellingDate,

    @Positive(message = "O preço de venda deve ser maior que zero.")
    @Digits(
        integer = 10,
        fraction = 2,
        message = "O preço deve ter até 10 dígitos inteiros e 2 decimais."
    )
    BigDecimal sellingPrice,

    @Positive(message = "O ID do cliente deve ser maior que zero.")
    Long customerId

) {}
