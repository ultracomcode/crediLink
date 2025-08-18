package com.main.CrediLink.exceptions;

public class AccountCodeNotFoundException extends RuntimeException {
    public AccountCodeNotFoundException(String message) {
        super(message);
    }
}
