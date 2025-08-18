package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "pix_cobranca")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PixEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime criacao;

    private String expiracao;

    @Column(name = "data_expiracao")
    private OffsetDateTime dataExpiracao;

    private String status;

    private String txid;

    private String location;

    private String valor;

    private String chave;

    private String pixCopiaECola;

    private String username;

    private String domain;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String accountcode;

    @Column(name = "qrcode", length = 5048)
    private String qrcode;

    @PrePersist
    public void createAccountCode() {
        this.accountcode = this.username + '@' + this.domain;
    }

    public void calcularDataExpiracao() {
        if (criacao != null && expiracao != null && expiracao.matches("\\d+")) {
            long segundos = Long.parseLong(expiracao);
            this.dataExpiracao = criacao.plusSeconds(segundos);
        }
    }

    @Transient
    public String getDataExpiracaoFormatada() {
        if (dataExpiracao != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return dataExpiracao.format(formatter);
        }
        return null;
    }
}
