package com.main.CrediLink.application.pix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.shared.enuns.PixStatus;

import java.time.Instant;
import java.time.LocalDateTime;

public record ResponsePixSave(
        String type,
        String message,
        Pix pix
) {

    public record Pix (
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            Instant vencimento,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            Instant pagamento,

            String pixCopiaECola,
            String txid,
            PixStatus status,
            String value
    ){
        public static Pix fromEntity(PixTransactionEntity entity) {
            return new Pix(
                    entity.getDataExpiracao(),
                    entity.getPaymentAt(),
                    entity.getPixCopiaECola(),
                    entity.getTxid(),
                    entity.getStatus(),
                    entity.getValor()
            );
        }
    }
}
