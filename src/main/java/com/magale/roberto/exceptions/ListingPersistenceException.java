package com.magale.roberto.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR)
public class ListingPersistenceException extends RuntimeException {

    public ListingPersistenceException(String message) {
        super(message);
    }

    public ListingPersistenceException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}