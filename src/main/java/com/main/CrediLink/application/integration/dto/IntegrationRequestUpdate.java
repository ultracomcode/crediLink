package com.main.CrediLink.application.integration.dto;

public record IntegrationRequestUpdate(
        String name,
        String tokenApi,
        String urlApi,
        String username,
        String password,
        String idContaBanco,
        String idContaContabil
) {
}
