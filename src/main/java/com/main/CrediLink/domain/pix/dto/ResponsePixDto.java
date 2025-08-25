package com.main.CrediLink.domain.pix.dto;

import com.main.CrediLink.domain.pix.PixEntity;

import java.time.OffsetDateTime;

public record ResponsePixDto(
        Long id,
        OffsetDateTime criacao,
        String pixCopiaECola,
        String status,
        String accountCode,
        String qrcode,
        String value,
        String obs

) {
    public static ResponsePixDto fromEntity(PixEntity entity) {
        return new ResponsePixDto(
                entity.getId(),
                entity.getCriacao(),
                entity.getPixCopiaECola(),
                entity.getStatus(),
                entity.getAccountcode(),
                "data:image/png;base64," + entity.getQrcode(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
