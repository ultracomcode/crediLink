package com.main.CrediLink.domain.pix.dto;

import com.main.CrediLink.domain.pix.PixEntity;

public record PixQrCodeResponseDto(
        String pixCopiaECola,
        String qrcode
) {
    public static PixQrCodeResponseDto fromEntity(PixEntity entity) {
        return new PixQrCodeResponseDto(
                entity.getPixCopiaECola(),
                "data:image/png;base64," + entity.getQrcode()
        );
    }
}
