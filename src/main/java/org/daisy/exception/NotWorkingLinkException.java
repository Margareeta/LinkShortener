package org.daisy.exception;

import static org.daisy.exception.Exceptions.NOT_WORKING_LINK_MESSAGE;

public class NotWorkingLinkException extends RuntimeException {
    private final static String message = NOT_WORKING_LINK_MESSAGE;

    public NotWorkingLinkException() {
        super(message);
    }
}
