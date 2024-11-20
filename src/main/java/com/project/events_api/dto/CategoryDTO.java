package com.project.events_api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa uma categoria de evento para transferência de dados.
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
public record CategoryDTO(
        Long id,

        @NotBlank(message = "O campo nome é obrigatório")
        String name,

        @NotBlank(message = "O campo descrição é obrigatório")
        String description
) {
}
