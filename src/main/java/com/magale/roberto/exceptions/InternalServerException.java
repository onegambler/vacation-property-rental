package com.magale.roberto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.INTERNAL_SERVER_ERROR,
        reason = "An unexpected error has occurred"
)
public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
