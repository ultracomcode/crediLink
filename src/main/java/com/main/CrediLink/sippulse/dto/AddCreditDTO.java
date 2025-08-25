package com.main.CrediLink.sippulse.dto;

import com.main.CrediLink.domain.pix.PixEntity;

public record AddCreditDTO(
        String username,
        String domain,
        String value,
        String obs
) {
    public static AddCreditDTO fromEntity(PixEntity pixEntity){
        return new AddCreditDTO(
                pixEntity.getUsername(),
                pixEntity.getDomain(),
                pixEntity.getValor(),
                pixEntity.getObservacao()
        );
    }
}
