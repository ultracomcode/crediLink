package com.main.CrediLink.integration.sippulse.dto;

import com.main.CrediLink.application.pix.entity.PixTransactionEntity;

public record AddCreditDTO(
        String username,
        String domain,
        String value,
        String obs
) {
    public static AddCreditDTO fromEntity(PixTransactionEntity pixTransactionEntity){
        return new AddCreditDTO(
                pixTransactionEntity.getUsername(),
                pixTransactionEntity.getDomain(),
                pixTransactionEntity.getValor(),
                pixTransactionEntity.getObservacao()
        );
    }
}
