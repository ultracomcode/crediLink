package com.main.CrediLink.exceptions;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path,
        String timestamp
) {}