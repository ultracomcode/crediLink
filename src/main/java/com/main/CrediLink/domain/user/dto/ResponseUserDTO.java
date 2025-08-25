package com.main.CrediLink.domain.user.dto;

import com.main.CrediLink.domain.pix.PixEntity;

public record UserDTO(
        String username,
        String domain,
        String value,
        String obs
) {
    public static UserDTO fromEntity(PixEntity entity) {
        return new UserDTO(
                entity.getUsername(),
                entity.getDomain(),
                entity.getValor(),
                entity.getObservacao()
        );
    }
}
