package com.project.events_api.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa um evento para transferência de dados.
 * @author Gabrielly Freire
 * @since 1.0
 */
public record EventDTO(
    Long id,

    @NotBlank(message = "O campo nome é obrigatório")
    String name,

    @NotBlank(message = "O campo descrição é obrigatório")
    String description,

    AddressDTO address,
    String linkMaps,

    @NotNull(message = "O campo status é obrigatório")
    EventStatusDTO status,

    @NotNull(message = "O campo tipo é obrigatório")
    EventTypeDTO type,

    @NotNull(message = "O campo categoria é obrigatório")
    CategoryDTO category,

    @NotNull(message = "O campo data de início é obrigatório")
    LocalDate startDate,

    @NotNull(message = "O campo data de fim é obrigatório")
    LocalDate endDate,

    String link,

    Integer participantsNumber,

    Long bannerId
) {
}
