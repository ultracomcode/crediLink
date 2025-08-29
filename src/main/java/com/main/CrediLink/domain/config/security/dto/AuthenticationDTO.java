package com.main.CrediLink.domain.config.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
