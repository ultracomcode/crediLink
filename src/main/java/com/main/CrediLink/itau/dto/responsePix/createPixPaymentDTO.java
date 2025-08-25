package com.main.CrediLink.itau.dto.responsePix;

public record createPixPaymentDTO(
    CalendarioDto calendario,
    String status,
    String txid,
    ValorDto valor,
    String chave,
    String pixCopiaECola,
    String location
) {
}
