package com.main.CrediLink.application.pix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.shared.enuns.PixStatus;
import com.main.CrediLink.shared.utils.FormatterUtils;

import java.time.Instant;
import java.time.LocalDateTime;

public record ResponsePixDto(
        Long id,
        String txid,
        String username,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant criacao,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant vencimento,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant pagamento,
        String pixCopiaECola,
        PixStatus status,
        String userAccountCode,
        String accountCode,
        String value,
        String obs
) {

    public static ResponsePixDto fromEntity(PixTransactionEntity entity) {
        return new ResponsePixDto(
                entity.getId(),
                entity.getTxid(),
                entity.getUser().getUsername(),
                entity.getCriacao(),
                entity.getDataExpiracao(),
                entity.getPaymentAt(),
                entity.getStatus() != PixStatus.AT ? null : entity.getPixCopiaECola(),
                entity.getStatus(),
                entity.getUsername(),
                entity.getAccountcode(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
