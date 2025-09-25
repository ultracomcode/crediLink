package com.main.CrediLink.domain.bancos.itau.entity;

import com.main.CrediLink.enuns.BankType;
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
public class TokenEntityItau {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id = 1L;

    @Column(name = "access_token", nullable = false, length = 5048)
    private String access_token;

    @Column(name = "token_type")
    private String token_type;

    @Column(name = "expires_in")
    private Long expires_in;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "banco_type")
    @Enumerated(EnumType.STRING)
    private BankType bankType;

    public boolean isExpired() {
        return expires_in == null || Instant.now().isAfter(Instant.ofEpochSecond(expires_in));
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
