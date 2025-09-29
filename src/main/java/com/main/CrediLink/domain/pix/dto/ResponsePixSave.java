package com.main.CrediLink.domain.pix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.domain.pix.PixTransactionEntity;
import com.main.CrediLink.enuns.PixStatus;

import java.time.LocalDateTime;

public record ResponsePixSave(
        String type,
        String message,
        Pix pix
) {

    public record Pix (
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime vencimento,
            String pixCopiaECola,
            PixStatus status,
            String value
    ){
        public static Pix fromEntity(PixTransactionEntity entity) {
            return new Pix(
                    entity.getDataExpiracao(),
                    entity.getPixCopiaECola(),
                    entity.getStatus(),
                    entity.getValor()
            );
        }
    }
}
