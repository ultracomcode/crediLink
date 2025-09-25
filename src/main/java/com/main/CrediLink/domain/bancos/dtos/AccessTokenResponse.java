package com.main.CrediLink.domain.bancos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessTokenResponse(
        @JsonProperty("access_token") String access_token,
        @JsonProperty("expires_in") Long expires_in
) {}
