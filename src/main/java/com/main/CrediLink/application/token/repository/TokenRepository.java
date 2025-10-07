package com.main.CrediLink.application.token.repository;

import com.main.CrediLink.application.token.entity.TokenEntity;
import com.main.CrediLink.shared.enuns.BankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByBankType(BankType bankType);

}
