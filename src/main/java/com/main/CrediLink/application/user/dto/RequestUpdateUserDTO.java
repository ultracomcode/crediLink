package com.main.CrediLink.application.user.dto;

import org.hibernate.validator.constraints.br.CNPJ;

public record RequestUpdateUserDTO(
        String name,
        String email,
        String password,
        @CNPJ
        String cpfCnpj,
        String phone,
        String idCrm,
        String idContrato
) {
}
