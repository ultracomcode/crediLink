package com.main.CrediLink.domain.pix.dto;

import com.main.CrediLink.domain.pix.PixTransactionEntity;

import java.time.OffsetDateTime;

public record ResponsePixDto(
        Long id,
        OffsetDateTime criacao,
        String pixCopiaECola,
        String status,
        String accountCode,
        String value,
        String obs

) {
    public static ResponsePixDto fromEntity(PixTransactionEntity entity) {
        return new ResponsePixDto(
                entity.getId(),
                entity.getCriacao(),
                entity.getPixCopiaECola(),
                entity.getStatus(),
                entity.getAccountcode(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
