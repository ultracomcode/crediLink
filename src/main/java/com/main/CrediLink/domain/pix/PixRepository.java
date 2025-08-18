package com.main.CrediLink.domain.pix;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PixRepository extends JpaRepository<PixEntity, Long> {
    Optional<PixEntity> findByTxid(String txid);
}
