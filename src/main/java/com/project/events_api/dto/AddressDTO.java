package com.project.events_api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa um endereço para transferência de dados.
 * @author Gabrielly Freire
 * @since 1.0
 */
public record AddressDTO (
        Long id,

        @NotBlank(message = "O campo cep é obrigatório")
        String postalCode,

        @NotBlank(message = "O campo logradouro é obrigatório")
        String street,

        @NotBlank(message = "O campo bairro é obrigatório")
        String neighborhood,

        @NotBlank(message = "O campo uf é obrigatório")
        String state,

        @NotBlank(message = "O campo cidade é obrigatório")
        String city,

        @NotBlank(message = "O campo número é obrigatório")
        String number,

        String complement
) {
}
