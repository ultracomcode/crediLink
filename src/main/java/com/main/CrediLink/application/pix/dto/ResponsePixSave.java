package com.main.CrediLink.application.pix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.shared.enuns.PixStatus;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record ResponsePixSave(
        String type,
        String message,
        Pix pix
) {

    public record Pix (
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
            Instant vencimento,
            String pixCopiaECola,
            String txid,
            String username,
            PixStatus status,
            String value
    ){}


    public static Pix fromEntity(PixTransactionEntity entity) {
        return new Pix(
                entity.getDataExpiracao(),
                entity.getPixCopiaECola(),
                entity.getTxid(),
                entity.getUsername(),
                entity.getStatus(),
                entity.getValor()
        );
    }
}
