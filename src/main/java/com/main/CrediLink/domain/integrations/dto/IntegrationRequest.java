package com.main.CrediLink.domain.integrations.dto;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;

public record IntegrationRequest (
        String name,
        String tokenApi,
        String urlApi,
        String username,
        String password,
        String idContaBanco,
        String idContaContabil,
        String type
){
}
