package com.german.novikov.enefit.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException {
    private final String message;
    private final Throwable cause;

    public ApplicationException(String message) {
        this.message = message;
        this.cause = null;
    }

    public ApplicationException(String message, Throwable cause) {
        this.cause = cause;
        this.message = message;
    }
}
