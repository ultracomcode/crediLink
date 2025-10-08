package com.main.CrediLink.application.webhook.dto;

public record WebhookNotificationDTO(
        String endToEndId,
        String txid,
        String valor,
        String horario,
        String infoPagador
) {
}
