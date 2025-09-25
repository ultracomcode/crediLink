package com.main.CrediLink.domain.bancos.itau.repository;

import com.main.CrediLink.domain.bancos.itau.entity.TokenEntityItau;
import com.main.CrediLink.enuns.BankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenItauRepository extends JpaRepository<TokenEntityItau, Long> {
    Optional<TokenEntityItau> findByBankType(BankType bankType);

}
