package com.main.CrediLink.domain.repository;

import com.main.CrediLink.domain.entity.PixPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixPaymentRepository extends JpaRepository<PixPaymentEntity, Long> {
    Optional<PixPaymentEntity> findByTxid(String txid);
}
