package com.main.CrediLink.domain.config.security.dto;

public record AuthenticationDTO(
        String login,
        String password
) {
}
