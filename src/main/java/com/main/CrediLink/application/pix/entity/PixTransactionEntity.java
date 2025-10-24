package com.main.CrediLink.application.pix.entity;

import com.main.CrediLink.application.user.UserEntity;
import com.main.CrediLink.shared.enuns.PixStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.time.Instant;

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
    private Instant criacao;

    @Column(name = "payment_at")
    private Instant paymentAt;

    @Column(name = "expiration_seconds")
    private String expiracao;

    @Column(name = "expiration_date")
    private Instant dataExpiracao;

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
    private void prePersist() {
        this.criacao = Instant.now();
        setUsernameAndDomain();
        setObservacaoIfNullOrVazio();
        calculateExpirationDate();
    }

    private void setUsernameAndDomain() {
        if (accountcode != null && accountcode.contains("@")) {
            String[] usernameDomain = accountcode.split("@", 2);
            this.username = usernameDomain[0];
            this.domain = usernameDomain[1];
        }
    }

    private void setObservacaoIfNullOrVazio() {
        if (this.observacao == null || this.observacao.isBlank()) {
            this.observacao = "Recarga Telefonia Via Api";
        }
    }


    public void calculateExpirationDate() {
        this.dataExpiracao = criacao.plusSeconds(600);
    }

}
