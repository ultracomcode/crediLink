package com.main.CrediLink.integration.banco.dto;

public record PixPaymentResponse(
    Calendario calendario,
    String status,
    String txid,
    Valor valor,
    String chave,
    String pixCopiaECola,
    String location
) {
    public record Calendario(String criacao,String expiracao) {}
    public record Valor(String original) { }
}
