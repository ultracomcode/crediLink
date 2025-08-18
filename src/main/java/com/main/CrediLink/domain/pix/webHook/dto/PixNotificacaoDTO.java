package com.main.CrediLink.domain.pix.webHook.dto;

public record PixNotificacaoDTO(
        String endToEndId,
        String txid,
        String valor,
        String horario,
        String infoPagador
) {
}
