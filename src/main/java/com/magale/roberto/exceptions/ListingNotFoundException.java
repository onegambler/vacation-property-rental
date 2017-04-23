package com.magale.roberto.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class ListingNotFoundException extends RuntimeException {

    public ListingNotFoundException(String propertyId) {
        super(String.format("Property with Id %s does not exist", propertyId));
    }
}