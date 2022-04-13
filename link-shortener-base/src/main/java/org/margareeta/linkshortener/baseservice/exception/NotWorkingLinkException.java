package org.margareeta.linkshortener.baseservice.exception;

import static org.margareeta.linkshortener.baseservice.exception.Exceptions.NOT_WORKING_LINK_MESSAGE;

public class NotWorkingLinkException extends RuntimeException {
    private final static String message = NOT_WORKING_LINK_MESSAGE;

    public NotWorkingLinkException() {
        super(message);
    }
}
