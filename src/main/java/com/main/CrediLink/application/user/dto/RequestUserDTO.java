package com.main.CrediLink.application.user.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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
        @CNPJ
        String cpfCnpj,

        @NotBlank
        String phone,

        @NotBlank
        String idCrm,

        @NotBlank
        String idContrato,

        @NotBlank
        String role
) {
}
