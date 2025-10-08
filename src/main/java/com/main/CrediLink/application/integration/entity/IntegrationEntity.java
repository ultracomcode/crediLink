package com.main.CrediLink.application.integration.entity;

import com.main.CrediLink.application.integration.enums.IntegrationStatus;
import com.main.CrediLink.application.integration.enums.IntegrationsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "integration")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntegrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "token_api")
    private String tokenApi;

    @Column(name = "identifier")
    private UUID identifier;

    @Column(name = "url_api")
    private String urlApi;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "id_conta_banco")
    private String idContaBanco;

    @Column(name = "id_conta_contabil")
    private String idContaContabil;

    @Column(name = "id_produto")
    private String idProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private IntegrationsType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private IntegrationStatus status;

    @PrePersist
    public void setCreationDate() {
        this.status = IntegrationStatus.A;
        this.identifier = UUID.randomUUID();
    }

}
