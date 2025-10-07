package com.main.CrediLink.integration.banco.dto;

public record PixPaymentRequest(
        Calendario calendario,
        Valor valor,
        String chave
) {
    public record Calendario(Integer expiracao) {}
    public record Valor(String original) {}
}
