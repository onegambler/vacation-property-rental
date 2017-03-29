package com.magale.roberto.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class ListingNotFoundException extends RuntimeException {

    public ListingNotFoundException(String message) {
        super(message);
    }
}