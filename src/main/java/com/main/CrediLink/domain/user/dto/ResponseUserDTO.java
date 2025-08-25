package com.main.CrediLink.domain.user.dto;

import com.main.CrediLink.domain.user.UserEntity;

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
                userEntity.getCpfCnpj(),
                userEntity.getPhone(),
                userEntity.getIdCrm(),
                userEntity.getStatus().toString(),
                userEntity.getRole().toString()
        );
    }
}
