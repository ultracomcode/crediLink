package com.main.CrediLink.application.user.dto;

import com.main.CrediLink.application.user.UserEntity;
import com.main.CrediLink.shared.utils.FormatterUtils;

import java.util.UUID;

public record ResponseUserDTO(
        Long id,
        UUID identifier,
        String name,
        String email,
        String username,
        String cpfCnpj,
        String phone,
        String idCrm,
        String idContrato,
        String status,
        String role
) {
    public static ResponseUserDTO fromEntity(UserEntity userEntity){
        return new ResponseUserDTO(
                userEntity.getId(),
                userEntity.getIdentifier(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                userEntity.getCpfCnpj() != null ? FormatterUtils.formatCpfCnpj(userEntity.getCpfCnpj()) : "",
                userEntity.getPhone() != null ? FormatterUtils.formatPhone(userEntity.getPhone()) : "",
                userEntity.getIdCrm(),
                userEntity.getIdContrato(),
                userEntity.getStatus().toString(),
                userEntity.getRole().toString()
        );
    }
}
