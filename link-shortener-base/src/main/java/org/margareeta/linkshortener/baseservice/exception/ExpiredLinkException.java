package org.margareeta.linkshortener.baseservice.exception;

import static org.margareeta.linkshortener.baseservice.exception.Exceptions.EXPRIRATION_MESSAGE;

public class ExpiredLinkException extends RuntimeException{
    private static final String message = EXPRIRATION_MESSAGE;
    public ExpiredLinkException(){
        super(message);
    }
}

