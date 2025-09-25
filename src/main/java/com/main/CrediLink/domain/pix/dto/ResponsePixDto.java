package com.main.CrediLink.domain.pix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.domain.pix.PixTransactionEntity;
import com.main.CrediLink.enuns.PixStatus;

import java.time.LocalDateTime;

public record ResponsePixDto(
        Long id,
        String username,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime criacao,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime pagamento,
        String pixCopiaECola,
        PixStatus status,
        String accountCode,
        String value,
        String obs

) {
    public static ResponsePixDto fromEntity(PixTransactionEntity entity) {
        return new ResponsePixDto(
                entity.getId(),
                entity.getUser().getUsername(),
                entity.getCriacao(),
                entity.getPaymentAt(),
                entity.getPixCopiaECola(),
                entity.getStatus(),
                entity.getAccountcode(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
