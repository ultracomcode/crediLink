package com.main.CrediLink.application.token.entity;

import com.main.CrediLink.shared.enuns.BankType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.Instant;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id = 1L;

    @Column(name = "access_token", nullable = false, length = 5048)
    private String accessToken;

    @Column(name = "token_type")
    private String tokenType;

    @Column(name = "expires_in")
    private Long expiresIn;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "banco_type")
    @Enumerated(EnumType.STRING)
    private BankType bankType;

    public boolean isExpired() {
        return expiresIn == null || Instant.now().isAfter(Instant.ofEpochSecond(expiresIn));
    }

    @PrePersist
    public void setCreationDate() {
        Instant now = Instant.now();
        this.createdAt = now;

    }

    @PreUpdate
    public void setUpdateDate() {
        this.updatedAt = Instant.now();
    }
}
