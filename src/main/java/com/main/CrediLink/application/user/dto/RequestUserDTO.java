package com.main.CrediLink.application.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestUserDTO(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotBlank
        String cpfCnpj,

        @NotBlank
        String phone,

        @NotBlank
        String idCrm,

        @NotBlank
        String idContrato
) {
}
