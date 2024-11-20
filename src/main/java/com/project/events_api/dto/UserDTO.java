package com.project.events_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa um usuário do sistema para transferência de dados.
 * @author Gabrielly Freire
 * @since 1.0
 */
public record UserDTO(
    Long id,

    @NotBlank(message = "O campo login é obrigatório")
    String login,

    @NotBlank(message = "O campo senha é obrigatório")
    @Min(value = 8, message = "A senha deve ter no mínimo 8 caracteres")
    String password,

    PersonDTO person
) {
}
