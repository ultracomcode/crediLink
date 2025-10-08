package com.main.CrediLink.integration.sippulse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subscriber")
public class Subscriber {

    @Id
    private Integer id;

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "contract_number")
    private String contract_number;
}
