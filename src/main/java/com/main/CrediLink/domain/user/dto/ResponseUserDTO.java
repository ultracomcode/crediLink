package com.main.CrediLink.domain.user.dto;

import com.main.CrediLink.domain.user.UserEntity;
import com.main.CrediLink.utils.FormatterUtils;

public record ResponseUserDTO(
        Long id,
        String name,
        String email,
        String username,
        String cpfCnpj,
        String phone,
        String idCrm,
        String status,
        String role
) {
    public static ResponseUserDTO fromEntity(UserEntity userEntity){
        return new ResponseUserDTO(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getUsername(),
                FormatterUtils.formatCpfCnpj(userEntity.getCpfCnpj()),
                FormatterUtils.formatPhone(userEntity.getPhone()),
                userEntity.getIdCrm(),
                userEntity.getStatus().toString(),
                userEntity.getRole().toString()
        );
    }
}
