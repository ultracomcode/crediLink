package com.main.CrediLink.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntityItau {
    @Id
    private Long id = 1L;

    @Column(name = "access_token", nullable = false, length = 5048)
    private String accessToken;

    @Column(name = "refresh_token", length = 5048)
    private String refreshToken;

    @Column(name = "expiration_time", nullable = false)
    private Instant expirationTime;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void setCreationDate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updatedAt = Instant.now();
    }
}
