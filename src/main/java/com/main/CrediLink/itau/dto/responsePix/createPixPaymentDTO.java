package com.main.CrediLink.domain.pix.dto.responsePix;

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
