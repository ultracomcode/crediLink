package com.main.CrediLink.shared.exceptions;

public class AccountCodeNotFoundException extends RuntimeException {
    public AccountCodeNotFoundException(String message) {
        super(message);
    }
}
