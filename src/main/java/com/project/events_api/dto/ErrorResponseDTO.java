package com.project.events_api.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * DTO para resposta de erro
 * @param timestamp Data e hora do erro
 * @param status Status do erro
 * @param message Mensagem de erro
 * @param path Caminho da requisição
 * @return ErrorResponseDTO
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
public record ErrorResponseDTO(
        LocalDateTime timestamp, HttpStatus status, String message, String path) {

}
