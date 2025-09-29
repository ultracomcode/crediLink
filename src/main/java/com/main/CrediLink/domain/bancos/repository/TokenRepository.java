package com.main.CrediLink.domain.bancos.repository;

import com.main.CrediLink.domain.bancos.entity.TokenEntity;
import com.main.CrediLink.enuns.BankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByBankType(BankType bankType);

}
