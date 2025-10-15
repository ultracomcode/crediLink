package com.main.CrediLink.application.integration.dto;

public record IntegrationRequest (
        String name,
        String tokenApi,
        String urlApi,
        String username,
        String password,
        String idContaBanco,
        String idContaContabil,
        String idProduto,
        String type
){
}
