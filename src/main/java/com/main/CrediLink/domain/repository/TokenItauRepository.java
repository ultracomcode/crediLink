package com.main.CrediLink.domain.repository;

import com.main.CrediLink.domain.entity.TokenEntityItau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenItauRepository extends JpaRepository<TokenEntityItau, Long> {
    Optional<TokenEntityItau> findTopByOrderByIdDesc();
}
