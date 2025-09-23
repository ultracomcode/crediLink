package com.main.CrediLink.domain.pix.dto;

import com.main.CrediLink.domain.pix.PixTransactionEntity;
import com.main.CrediLink.enuns.PixStatus;

import java.time.OffsetDateTime;

public record ResponsePixDto(
        Long id,
        OffsetDateTime criacao,
        OffsetDateTime pagamento,
        String pixCopiaECola,
        PixStatus status,
        String accountCode,
        String value,
        String obs

) {
    public static ResponsePixDto fromEntity(PixTransactionEntity entity) {
        return new ResponsePixDto(
                entity.getId(),
                entity.getCriacao(),
                entity.getPayment_at(),
                entity.getPixCopiaECola(),
                entity.getStatus(),
                entity.getAccountcode(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
