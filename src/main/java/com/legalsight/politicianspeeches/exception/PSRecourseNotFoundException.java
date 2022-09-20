package com.legalsight.politicianspeeches.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PSRecourseNotFoundException extends PSServiceException {

    public PSRecourseNotFoundException() {
        super();
    }

    public PSRecourseNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PSRecourseNotFoundException(final String message) {
        super(message);
    }

    public PSRecourseNotFoundException(final Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
