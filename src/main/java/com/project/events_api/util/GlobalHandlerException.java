package com.project.events_api.util;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.events_api.dto.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

/**
 * Manipulador de exceções globais
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@ControllerAdvice
public class GlobalHandlerException {

    /**
     * Manipula exceções de recurso não encontrado
     * 
     * @param ex      Exceção de recurso não encontrado
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.NOT_FOUND, ex.getMessage(),
                        request.getRequestURI()));
    }

    /**
     * Manipula exceções de erro de negócio
     * 
     * @param ex      Exceção de erro de negócio
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getHttpStatusCode())
                .body(new ErrorResponseDTO(LocalDateTime.now(), ex.getHttpStatusCode(), ex.getMessage(),
                        request.getRequestURI()));
    }

    /**
     * Manipula exceções de validação
     * 
     * @param ex      Exceção de validação
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage(),
                        request.getRequestURI()));
    }

    /**
     * Manipula exceções de violação de restrição
     * 
     * @param ex      Exceção de violação de restrição
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(ConstraintViolationException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage(),
                        request.getRequestURI()));
    }

    /**
     * Manipula exceções de violação de integridade de dados
     * 
     * @param ex      Exceção de violação de integridade de dados
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST, ex.getMessage(),
                        request.getRequestURI()));
    }

    /**
     * Manipula exceções de erro interno do servidor
     * 
     * @param ex      Exceção de erro interno do servidor
     * @param request Requisição HTTP
     * @return ResponseEntity<ErrorResponseDTO>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                        request.getRequestURI()));
    }

}
