package com.main.CrediLink.sippulse.entity;

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

    @Column(name = "first_name")
    private String first_name;
}
