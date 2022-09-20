package com.legalsight.politicianspeeches.exception;

import org.springframework.http.HttpStatus;

public abstract class PSServiceException extends Exception{
    public PSServiceException() {
        super();
    }

    public PSServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PSServiceException(final String message) {
        super(message);
    }

    public PSServiceException(final Throwable cause) {
        super(cause);
    }

    public abstract HttpStatus getStatus();
}
