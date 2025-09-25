package com.main.CrediLink.domain.pix;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.main.CrediLink.domain.user.UserEntity;
import com.main.CrediLink.enuns.PixStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "pix_transaction")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PixTransactionEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime criacao;

    @Column(name = "payment_at")
    private LocalDateTime paymentAt;

    @Column(name = "expiration_seconds")
    private String expiracao;

    @Column(name = "expiration_date")
    private LocalDateTime dataExpiracao;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    @Column(name = "txid")
    private String txid;

    @Column(name = "location")
    private String location;

    @Column(name = "amount")
    private String valor;

    @Column(name = "key_pix")
    private String chave;

    @Column(name = "pix_copy_paste")
    private String pixCopiaECola;

    @Column(name = "username")
    private String username;

    @Column(name = "domain")
    private String domain;

    @Column(name = "observation")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "account_code")
    private String accountcode;

    @PrePersist
    public void setUsernameAndDomain() {
        String[] usernameDomain = accountcode.split("@");
        this.username = usernameDomain[0];
        this.domain = usernameDomain[1];
    }

    public void calculateExpirationDate() {
        if (criacao != null && expiracao != null && expiracao.matches("\\d+")) {
            long segundos = Long.parseLong(expiracao);
            this.dataExpiracao = criacao.plusSeconds(segundos);
        }
    }

    public void setCriacaoFromIsoZ(String isoDateTimeZ) {
        this.criacao = parseIsoZToLocalDateTime(isoDateTimeZ);
    }

    public void setPaymentAtFromIsoZ(String isoDateTimeZ) {
        this.paymentAt = parseIsoZToLocalDateTime(isoDateTimeZ);
    }

    private LocalDateTime parseIsoZToLocalDateTime(String isoDateTimeZ) {
        try {
            return Instant.parse(isoDateTimeZ)
                    .atZone(ZoneId.of("America/Sao_Paulo"))
                    .toLocalDateTime();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de data/hora inválido: " + isoDateTimeZ, e);
        }
    }
}
