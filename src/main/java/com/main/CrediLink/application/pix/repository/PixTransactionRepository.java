package com.main.CrediLink.application.pix.repository;

import com.main.CrediLink.application.pix.entity.PixTransactionEntity;
import com.main.CrediLink.shared.enuns.PixStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PixTransactionRepository extends JpaRepository<PixTransactionEntity, Long> {
    Optional<PixTransactionEntity> findByTxid(String txid);

    Page<PixTransactionEntity> findByUserId(Long userId, Pageable pageable);

    @Modifying
    @Query("""
        UPDATE PixTransactionEntity p
        SET p.status = :status
        WHERE 
            p.txid = :txid
          AND 
            p.status IN ('AT')
    """)
    int updateStatusIfCancellable(@Param("txid") String txid, @Param("status") PixStatus status);

    List<PixTransactionEntity> findByStatus(PixStatus status);
}
