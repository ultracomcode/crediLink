package com.main.CrediLink.domain.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
