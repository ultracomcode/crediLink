package com.main.CrediLink.domain.integrations.dto;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationStatus;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;

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
