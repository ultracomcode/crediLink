package com.main.CrediLink.application.user.dto;

public record RequestUpdateUserDTO(
        String name,
        String email,
        String password,
        String cpfCnpj,
        String phone,
        String idCrm,
        String idContrato
) {
}
