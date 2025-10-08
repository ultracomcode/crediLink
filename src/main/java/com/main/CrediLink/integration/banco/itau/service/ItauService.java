package com.main.CrediLink.integration.banco.itau.service;


import com.main.CrediLink.application.token.exceptions.TokenException;
import com.main.CrediLink.integration.banco.itau.feing.FeingAccessToken;
import com.main.CrediLink.integration.banco.itau.feing.FeingPixRequest;
import com.main.CrediLink.integration.banco.interfaces.Banco;
import com.main.CrediLink.application.token.dto.AccessTokenResponse;
import com.main.CrediLink.integration.banco.dto.PixPaymentRequest;
import com.main.CrediLink.integration.banco.dto.PixPaymentResponse;
import com.main.CrediLink.application.token.entity.TokenEntity;
import com.main.CrediLink.application.token.repository.TokenRepository;
import com.main.CrediLink.shared.enuns.BankType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.UUID;

@Service
public class ItauService implements Banco {

    private final FeingAccessToken feingAccessToken;
    private final FeingPixRequest feingPixRequest;
    private final TokenRepository tokenRepository;

    @Value("${itau.client-id}")
    private String clientId;

    @Value("${itau.client-secret}")
    private String clientSecret;

    public ItauService(FeingAccessToken feingAccessToken, FeingPixRequest feingPixRequest, TokenRepository tokenRepository) {
        this.feingAccessToken = feingAccessToken;
        this.feingPixRequest = feingPixRequest;
        this.tokenRepository = tokenRepository;
    }

    public TokenEntity getToken() {
        TokenEntity token = tokenRepository.findByBankType(BankType.IT)
                .orElse(null);

        if (token == null || token.isExpired()){

            token = getAccessToken();
        }

        return token;
    }

    @Override
    public TokenEntity getAccessToken() {
        try {

            var accessToken = feingAccessToken.getAccessToken("1",UUID.randomUUID().toString(),buildRequestBody());

            return saveToken(accessToken);

        } catch (Exception e) {

            throw new TokenException("Não foi possível obter o token", e);
        }

    }

    @Override
    public PixPaymentResponse createCharge(PixPaymentRequest pixPaymentRequest) {

        try {

            var tokenEntity = getToken();
            String authorizationHeader = tokenEntity.getTokenType() + " " + tokenEntity.getAccessToken();

            var requestPix = feingPixRequest.createCharge(
                    authorizationHeader,
                    UUID.randomUUID().toString(),
                    "1",
                    pixPaymentRequest
            );

            return requestPix;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private TokenEntity saveToken(AccessTokenResponse  accessToken) {
        var tokenEntity = new TokenEntity();

        BeanUtils.copyProperties(accessToken, tokenEntity);
        tokenEntity.setBankType(BankType.IT);

        return tokenRepository.save(tokenEntity);
    }

    private MultiValueMap<String, String> buildRequestBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "client_credentials");
        return body;
    }
}
