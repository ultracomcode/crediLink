package com.main.CrediLink.domain.dto;

import com.main.CrediLink.domain.entity.PixPaymentEntity;

public record UserDTO(
        String username,
        String domain,
        String value,
        String obs
) {
    public static UserDTO fromEntity(PixPaymentEntity entity) {
        return new UserDTO(
                entity.getUsername(),
                entity.getDomain(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
