package com.main.CrediLink.domain.bancos.itau.service;


import com.main.CrediLink.domain.bancos.exceptions.TokenException;
import com.main.CrediLink.domain.bancos.itau.feing.FeingAccessToken;
import com.main.CrediLink.domain.bancos.itau.feing.FeingPixRequest;
import com.main.CrediLink.domain.bancos.interfaces.Banco;
import com.main.CrediLink.domain.bancos.dtos.AccessTokenResponse;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentRequest;
import com.main.CrediLink.domain.bancos.dtos.PixPaymentResponse;
import com.main.CrediLink.domain.bancos.itau.entity.TokenEntityItau;
import com.main.CrediLink.domain.bancos.itau.repository.TokenItauRepository;
import com.main.CrediLink.enuns.BankType;
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
    private final TokenItauRepository tokenRepository;

    @Value("${itau.client-id}")
    private String clientId;

    @Value("${itau.client-secret}")
    private String clientSecret;

    public ItauService(FeingAccessToken feingAccessToken, FeingPixRequest feingPixRequest, TokenItauRepository tokenRepository) {
        this.feingAccessToken = feingAccessToken;
        this.feingPixRequest = feingPixRequest;
        this.tokenRepository = tokenRepository;
    }

    public TokenEntityItau getToken() {
        TokenEntityItau token = tokenRepository.findByBankType(BankType.IT)
                .orElse(null);

        if (token == null || token.isExpired()){

            token = getAccessToken();
        }

        return token;
    }

    @Override
    public TokenEntityItau getAccessToken() {
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
            var requestPix = feingPixRequest.createCharge(
                    "Bearer " + getToken().getAccess_token(),
                    UUID.randomUUID().toString(),
                    "1",
                    pixPaymentRequest
            );

            return requestPix;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private TokenEntityItau saveToken(AccessTokenResponse  accessToken) {
        var tokenEntity = new TokenEntityItau();

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
