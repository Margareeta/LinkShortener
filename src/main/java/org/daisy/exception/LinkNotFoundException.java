package org.daisy.exception;

import static org.daisy.exception.Exceptions.NOT_FOUND_MESSAGE;

public class LinkNotFoundException extends RuntimeException{
    private static final String message = NOT_FOUND_MESSAGE;

    public LinkNotFoundException(){
        super(message);
    }
}
