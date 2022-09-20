package com.legalsight.politicianspeeches.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PSOperationNotSupportedException extends PSServiceException {
    public PSOperationNotSupportedException() {
        super();
    }

    public PSOperationNotSupportedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PSOperationNotSupportedException(final String message) {
        super(message);
    }

    public PSOperationNotSupportedException(final Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
