package com.legalsight.politicianspeeches.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PSResourceAlreadyExistsException extends PSServiceException {
    public PSResourceAlreadyExistsException() {
        super();
    }

    public PSResourceAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PSResourceAlreadyExistsException(final String message) {
        super(message);
    }

    public PSResourceAlreadyExistsException(final Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
