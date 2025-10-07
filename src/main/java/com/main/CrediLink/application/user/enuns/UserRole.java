package com.main.CrediLink.application.user.enuns;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
