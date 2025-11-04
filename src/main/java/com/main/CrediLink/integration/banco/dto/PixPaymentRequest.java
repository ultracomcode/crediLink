package com.main.CrediLink.integration.banco.dto;

public record PixPaymentRequest(
        Valor valor,
        String chave
) {
    public record Valor(String original) {}
}
