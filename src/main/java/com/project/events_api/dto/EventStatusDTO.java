package com.project.events_api.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa o status de um evento para transferência de dados.
 * @author Gabrielly Freire
 * @since 1.0
 */
public record EventStatusDTO(
        Long id,

        @NotBlank(message = "O campo nome é obrigatório")
        String name,

        String description
) {
}
