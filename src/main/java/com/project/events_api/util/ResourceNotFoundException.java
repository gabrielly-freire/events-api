package com.project.events_api.util;

/**
 * Exceção personalizada para indicar que um recurso não foi encontrado.
 * Exceção estendida de RuntimeException.
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
    
}
