package com.project.events_api.util;

import org.springframework.http.HttpStatus;

/**
 * Exceção customizada para representar erros de negócio.
 * Exceção estendida de RuntimeException.
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
public class BusinessException  extends RuntimeException {

    private final HttpStatus httpStatusCode;

    public BusinessException(String message, HttpStatus httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
    
}
