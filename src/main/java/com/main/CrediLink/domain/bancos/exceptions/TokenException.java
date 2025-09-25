package com.main.CrediLink.domain.bancos.exceptions;

public class TokenException extends RuntimeException {
    public TokenException(String message, Exception e) {
        super(message);
    }
}
