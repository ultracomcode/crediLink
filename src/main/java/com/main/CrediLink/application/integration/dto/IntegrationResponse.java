package com.main.CrediLink.application.integration.dto;

import com.main.CrediLink.application.integration.entity.IntegrationEntity;
import com.main.CrediLink.application.integration.enums.IntegrationStatus;
import com.main.CrediLink.application.integration.enums.IntegrationsType;

public record IntegrationResponse(
        Long id,
        String name,
        String urlApi,
        String username,
        String idContaBanco,
        String idContaContabil,
        String idProduto,
        IntegrationsType type,
        IntegrationStatus status
) {
    public static IntegrationResponse fromEntity(IntegrationEntity integration){
        return new IntegrationResponse(
                integration.getId(),
                integration.getName(),
                integration.getUrlApi(),
                integration.getUsername(),
                integration.getIdContaBanco(),
                integration.getIdContaContabil(),
                integration.getIdProduto(),
                integration.getType(),
                integration.getStatus()
        );
    }
}
