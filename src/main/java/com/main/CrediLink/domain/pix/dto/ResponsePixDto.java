package com.main.CrediLink.domain.pix.dto;

import com.main.CrediLink.domain.pix.PixEntity;

public record PixQrCodeResponseDto(
        Long id,
        String criacao,
        String pixCopiaECola,
        String status,
        String accountCode,
        String qrcode,
        String value,
        String obs

) {
    public static PixQrCodeResponseDto fromEntity(PixEntity entity) {
        return new PixQrCodeResponseDto(
                entity.getPixCopiaECola(),
                "data:image/png;base64," + entity.getQrcode()
        );
    }
}
