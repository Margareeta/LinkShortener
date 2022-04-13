package org.margareeta.linkshortener.baseservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.margareeta.linkshortener.baseservice.exception.Exceptions.*;

@RestControllerAdvice
public class LinkServiceExceptionHandler {

    @ExceptionHandler({NotWorkingLinkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotWorkingLinkException() {
        return NOT_WORKING_LINK_MESSAGE;
    }

    @ExceptionHandler({LinkNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLinkNotFoundException() {
        return NOT_FOUND_MESSAGE;
    }

    @ExceptionHandler({ExpiredLinkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExpireLinkException() {
        return EXPRIRATION_MESSAGE;
    }
}
