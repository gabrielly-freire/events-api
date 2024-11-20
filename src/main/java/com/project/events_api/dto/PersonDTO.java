package com.project.events_api.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Classe que representa uma pessoa para transferência de dados.
 * @author Gabrielly Freire
 * @since 1.0
 */
public record PersonDTO(
        Long id,

        @NotBlank(message = "O campo nome é obrigatório")
        String name,

        @NotBlank(message = "O campo cpf é obrigatório")
        @CPF(message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "O campo email é inválido")
        String email,

        @NotBlank(message = "O campo telefone é obrigatório")
        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido")
        String phone,

        @NotNull(message = "O campo data de nascimento é obrigatório")
        LocalDate birthDate,

        AddressDTO address
) {
}
