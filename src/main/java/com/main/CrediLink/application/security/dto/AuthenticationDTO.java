package com.main.CrediLink.application.security.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
