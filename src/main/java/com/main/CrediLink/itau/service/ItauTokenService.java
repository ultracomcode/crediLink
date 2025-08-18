package com.main.CrediLink.itau.service;


import com.main.CrediLink.domain.token.TokenEntityItau;
import com.main.CrediLink.domain.token.TokenItauRepository;
import com.main.CrediLink.itau.dto.FetchTokenResponse;
import com.main.CrediLink.itau.feing.ItauTokenClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Service
public class ItauTokenService {

    private final ItauTokenClient itauTokenClient;
    private final TokenItauRepository tokenRepository;

    @Value("${itau.client-id}")
    private String clientId;

    @Value("${itau.client-secret}")
    private String clientSecret;

    public ItauTokenService(ItauTokenClient itauTokenClient, TokenItauRepository tokenRepository) {
        this.itauTokenClient = itauTokenClient;
        this.tokenRepository = tokenRepository;
    }

    public String getToken() {
        TokenEntityItau accessToken = tokenRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new RuntimeException("Token Não encontrado"));
        return accessToken.getAccessToken();
    }

    public FetchTokenResponse fetchToken() {
        MultiValueMap<String, String> body = buildRequestBody(Map.of("grant_type", "client_credentials"));
        return itauTokenClient.getToken("1", "2", body);
    }

    public FetchTokenResponse refreshToken(String refreshToken) {
        MultiValueMap<String, String> body = buildRequestBody(Map.of(
                "grant_type", "refresh_token",
                "refresh_token", refreshToken));
        return itauTokenClient.getToken("1", "2", body);
    }

    private MultiValueMap<String, String> buildRequestBody(Map<String, String> extraParams) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        extraParams.forEach(body::add);
        return body;
    }
}
