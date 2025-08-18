package com.main.CrediLink.domain.dto;

import com.main.CrediLink.domain.entity.PixPaymentEntity;

public record PixQrCodeResponseDto(
        String pixCopiaECola,
        String qrcode
) {
    public static PixQrCodeResponseDto fromEntity(PixPaymentEntity entity) {
        return new PixQrCodeResponseDto(
                entity.getPixCopiaECola(),
                "data:image/png;base64," + entity.getQrcode()
        );
    }
}
