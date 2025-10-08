package com.main.CrediLink.application.user.dto;

import com.main.CrediLink.application.user.UserEntity;

public record UserDTO(
    String username,
    boolean admin
){
    public static UserDTO fromEntity(UserEntity userEntity){
        return new UserDTO(
            userEntity.getUsername(),
            userEntity.isAdmin()
        );
    }
}
