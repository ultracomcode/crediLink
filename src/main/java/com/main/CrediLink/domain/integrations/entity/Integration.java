package com.main.CrediLink.domain.integrations.entity;

import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "integration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Integration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "token_api")
    private String tokenApi;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "id_conta_banco")
    private String idContaBanco;

    @Column(name = "id_conta_contabil")
    private String idContaContabil;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private IntegrationsType tipo;

    @Column(name = "status")
    private String status;




}
