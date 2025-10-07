package com.main.CrediLink.application.token.exceptions;

public class TokenException extends RuntimeException {
    public TokenException(String message, Exception e) {
        super(message);
    }
}
