package org.daisy.exception;

import static org.daisy.exception.Exceptions.EXPRIRATION_MESSAGE;

public class ExpiredLinkException extends RuntimeException{
    private static final String message = EXPRIRATION_MESSAGE;
    public ExpiredLinkException(){
        super(message);
    }
}

