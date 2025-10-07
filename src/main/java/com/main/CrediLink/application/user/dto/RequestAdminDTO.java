package com.main.CrediLink.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestAdminDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String role
) {
}
